package com.whp.buy.user.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Dao.SysDepartmentDao;
import com.whp.buy.user.Dao.SysModuleDao;
import com.whp.buy.user.Dao.SysUserDao;
import com.whp.buy.user.Interface.SysModuleInterface;
import com.whp.buy.utils.util.ImgUtil;
import com.whp.buy.utils.util.ImgUtilTg;
import com.whp.buy.utils.util.JWTUtil;
import com.whp.buy.utils.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/Sysmodule")
public class SysModuleController {
    @Autowired
    private SysModuleInterface sysModuleInterface;
    @Autowired
    private SysDepartmentDao sysDepartmentDao;
    @Autowired
    private SysModuleDao sysModuleDao;
    @Autowired
    private SysUserDao sysUserDao;
    @Value("${fast.url}")
    private String url;

    @PostMapping("getModuleByUserP")
    public JSONObject getModuleByUserP(String departmentcode, HttpServletRequest request) {
        JSONObject jsons = new JSONObject();
        String userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        JSONObject json = sysModuleInterface.getModuleByDepartment(departmentcode);
        JSONObject json1 = sysModuleInterface.getModuleByUser(userid);
        Map<String, Object> map = sysDepartmentDao.getParentBydepartmentcode(departmentcode);
        JSONObject json2 = sysModuleInterface.getModuleByDepartmentp(map.get("parentdepartmentcode").toString());
        Map usermap = sysUserDao.getSubUserByUser(userid);
        JSONObject json3 = sysModuleInterface.getModuleByRole(usermap.get("roleid").toString());

        jsons.put("departmodule", json);
        jsons.put("usermodule", json1);
        if (map.get("parentdepartmentcode").toString().equals(usermap.get("departmentcode"))) {
            jsons.put("parentdepartmentcode", json3);
        } else {
            jsons.put("parentdepartmentcode", json2);
        }


        return jsons;
    }

    @PostMapping("departmentModuleUpdate")
    public JSONObject departmentModuleUpdate(String departmentcode, @RequestParam("moduleid[]") String moduleid[]) {
        JSONObject json = sysModuleInterface.departmentModuleUpdate(departmentcode, moduleid);
        return json;
    }

    @PostMapping("getModuleByDepartmentCode")
    public JSONObject getModuleByDepartmentCode(String roleid, String departmentcode) {
        JSONObject jsons = new JSONObject();
        JSONObject json = sysModuleInterface.getModuleByDepartment(departmentcode);
        JSONObject json1 = sysModuleInterface.getModuleByRole(roleid);


        jsons.put("departmodule", json);
        jsons.put("rolemodule", json1);
        return jsons;
    }

    @PostMapping("roleModuleUpdate")
    public JSONObject roleModuleUpdate(String roleid, @RequestParam("moduleid[]") String moduleid[]) {
        JSONObject json = sysModuleInterface.roleModuleUpdate(roleid, moduleid);
        return json;
    }

    @PostMapping("getModule")
    public JSONObject getModule() {
        JSONObject json = sysModuleInterface.getModule();
        return json;
    }

    @PostMapping("moduleAdd")
    public JSONObject moduleAdd(@RequestParam(value = "css", required = false) MultipartFile css, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        SnowFlake snowFlake = new SnowFlake(1, 1);
        String userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("userid", userid);
        if (css == null || css.equals("")) {
            map.put("css", null);
        } else {
            map.put("css", url + "/" + new ImgUtilTg().FileImg(css, 200, 200));
        }
        json = sysModuleInterface.moduleAdd(map);
        if (json.getInteger("code") == 100) {
            sysModuleDao.departmentModuleAdd("1", json.get("moduleid").toString(), String.valueOf(snowFlake.nextId()));
            sysModuleDao.roleModuleAdd("1", json.get("moduleid").toString());
        }
        return json;
    }

    @PostMapping("moduleUpdate")
    public JSONObject moduleUpdate(@RequestParam(value = "css", required = false) MultipartFile css, @RequestParam Map<String, Object> map) {
        JSONObject json = new JSONObject();
        if (css == null || css.equals("")) {
            map.put("css", null);
        } else {
            map.put("css", url + "/" + new ImgUtilTg().FileImg(css, 200, 200));
        }
        json = sysModuleInterface.moduleUpdate(map);
        return json;
    }

    @PostMapping("sysmoduleUpdateDel")
    public JSONObject sysmoduleUpdateDel(@RequestParam("moduleid[]") String[] moduleid, @RequestParam Map<String, Object> map) {
        JSONObject json = new JSONObject();
        if (map.get("style").equals("4")) {
            map.put("state", "0");
        } else if (map.get("style").equals("5")) {
            map.put("state", "1");
        } else {
            map.put("isuse", "1");
        }
        for (String id : moduleid) {
            map.put("moduleid", id);
            json = sysModuleInterface.sysmoduleUpdateDel(map);

        }
        return json;
    }

}
