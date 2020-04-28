package com.whp.buy.user.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Dao.BuyModuleBuyDao;
import com.whp.buy.user.Dao.BuyRoleDao;
import com.whp.buy.user.Dao.SysBuyDao;
import com.whp.buy.user.Interface.SubBuyInterface;
import com.whp.buy.utils.util.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Subbuy")
public class SubBuyController {
    @Autowired
    private SubBuyInterface subBuyInterface;
    @Autowired
    private SysBuyDao sysBuyDao;
    @Autowired
    private BuyModuleBuyDao buyModuleBuyDao;
    @Autowired
    private BuyRoleDao buyRoleDao;
    @Value("${fdfs.thumb-image.width}")
    private int width;
    @Value("${fdfs.thumb-image.height}")
    private int height;
    @Value("${fast.url}")
    private String url;

    @PostMapping("subBuyAdd")
    public JSONObject subBuyAdd(@RequestParam(value = "img", required = false) MultipartFile img, @RequestParam("moduleid[]") String[] moduleid, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        List<Map<String, Object>> list = sysBuyDao.getBuyForBuy();
        String sysuserid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");

        if (img == null || img.equals("")) {
            map.put("img", null);
        } else {
            map.put("img", url + "/" + new ImgUtilTg().FileImg(img, width, height));
        }
        map.put("sysuserid", sysuserid);
        SnowFlake snowFlake = new SnowFlake(1, 1);
        long pid = snowFlake.nextId();//商家id
        long pidr = snowFlake.nextId();//角色id
        long pidu = snowFlake.nextId();//商家用户id
        map.put("pid", pid);
        map.put("pidr", pidr);
        map.put("pidu", pidu);
        if (map.containsKey("password")) {
            String pwd = MD5Util.MD5(map.get("password").toString());
            map.put("password", pwd);
        }
        json = subBuyInterface.subBuyAdd(map);

        if (json.getInteger("code") == 100) {
            List<Map<String, Object>> list1 = buyModuleBuyDao.buyModuleBuySelect(map.get("pid").toString());
            for (String id : moduleid) {
                long pida = snowFlake.nextId();//admin权限
                map.put("moduleid", id);
                map.put("pida", pida);
                buyModuleBuyDao.buyModuleBuyAdd(map);
                buyModuleBuyDao.subBuyModuleAdd(map);
                for (Map mmap : list) {
                    if ((mmap.get("type").toString().equals("1")) && (mmap.get("pid").toString().equals(id))) {
                        long pidbrm = snowFlake.nextId();//角色权限id
                        map.put("pidbrm", pidbrm);
                        buyRoleDao.buyRoleModuleAdd(map);
                        continue;
                    }
                }
            }
        }

        return json;
    }

    @PostMapping("subBuySelect")
    public JSONObject subBuySelect(@RequestParam Map<String, Object> map) {
        JSONObject json = new JSONObject();
        json = subBuyInterface.subBuySelect(map);

        return json;
    }

    @PostMapping("subBuyUpdate")
    public JSONObject subBuyUpdate(@RequestParam(value = "img", required = false) MultipartFile img, @RequestParam("moduleid[]") String[] moduleid, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        SnowFlake snowFlake = new SnowFlake(1, 1);
        String sysuserid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        if (img != null || !("").equals(img)) {
            map.put("img", url + "/" + new ImgUtilTg().FileImg(img, width, height));
        } else {
            map.put("img", null);
        }
        map.put("sysuserid", sysuserid);
        json = subBuyInterface.subBuyUpdate(map);//修改商家基本信息

        if (json.getInteger("code") == 100) {
            String buyid = map.get("pid").toString();
            List<Map<String, Object>> list = buyModuleBuyDao.buyModuleBuySelect(buyid);
            for (int i = 0; i < list.size(); i++) {
                int cz = 0;
                for (int j = 0; j < moduleid.length; j++) {
                    if (!moduleid[j].equals("") && moduleid[j] != "") {
                        if (list.get(i).get("buy_module_id").toString().equals(moduleid[j])) {
                            cz += 1;
                        }
                    }
                }
                if (cz == 0) {
                    buyModuleBuyDao.buyModuleBuyDel(buyid, list.get(i).get("buy_module_id").toString());
                    buyModuleBuyDao.subBuyModuleDel(buyid, list.get(i).get("buy_module_id").toString());
                    buyRoleDao.buyRoleModuleDel(buyid, list.get(i).get("buy_module_id").toString());
                }
            }
            List<Map<String, Object>> list1 = buyRoleDao.buyRoleSelectByBuyid(buyid);

            for (int j = 0; j < moduleid.length; j++) {
                int cz = 0;
                if (!moduleid[j].equals("") && moduleid[j] != "") {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).get("buy_module_id").toString().equals(moduleid[j])) {
                            cz += 1;
                        }
                    }
                    if (cz == 0) {
                        map.put("pida", snowFlake.nextId());
                        map.put("pid", buyid);
                        map.put("moduleid", moduleid[j]);
                        buyModuleBuyDao.buyModuleBuyAdd(map);
                        buyModuleBuyDao.subBuyModuleAdd(map);
                        map.put("pidbrm", snowFlake.nextId());
                        for (Map mmap : list1) {
                            map.put("pidr", mmap.get("pid").toString());
                            buyRoleDao.buyRoleModuleAdd(map);
                        }

                    }

                }
            }


        }

        return json;
    }

    @PostMapping("subBuyDelUpdate")
    public JSONObject subBuyDelUpdate(@RequestParam("pid[]") String[] pid, @RequestParam Map<String, Object> map) {
        JSONObject json = new JSONObject();
        for (String id : pid) {
            map.put("pid", id);
            json = subBuyInterface.subBuyDelUpdate(map);
        }
        return json;
    }

    @PostMapping("subBuySelectsub")
    public JSONObject subBuySelectsub() {
        JSONObject json = subBuyInterface.subBuySelectsub();
        return json;
    }

    @PostMapping("subBuySelectByNum")
    public JSONObject subBuySelectByNum(@RequestParam Map<String, Object> map) {
        JSONObject json = subBuyInterface.subBuySelectByNum(map);
        return json;
    }
}
