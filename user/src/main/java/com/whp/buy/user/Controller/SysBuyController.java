package com.whp.buy.user.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Interface.SysBuyInterface;
import com.whp.buy.utils.util.ImgUtil;
import com.whp.buy.utils.util.ImgUtilTg;
import com.whp.buy.utils.util.JWTUtil;
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
@RequestMapping("/Sysbuy")
public class SysBuyController {
    @Value("${fast.url}")
    private String url;

    @Autowired
    private SysBuyInterface sysBuyInterface;

    @PostMapping("buyModuleAdd")
    public JSONObject buyModuleAdd(@RequestParam(value = "css", required = false) MultipartFile css, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String sysuserid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("sysuserid", sysuserid);
        if (css == null || css.equals("")) {
            map.put("css", null);
        } else {
            map.put("css", url + "/" + new ImgUtilTg().FileImg(css, 200, 200));
        }

        json = sysBuyInterface.buyModuleAdd(map);

        return json;

    }

    @PostMapping("getBuyModule")
    public JSONObject getBuyModule() {
        JSONObject json = sysBuyInterface.getBuyModule();
        return json;
    }

    @PostMapping("getBuyForBuy")
    public JSONObject getBuyForBuy() {
        JSONObject json = sysBuyInterface.getBuyForBuy();
        return json;
    }

    @PostMapping("getBuyModuleBuy")
    public JSONObject getBuyModuleBuy(@RequestParam Map<String, Object> map) {
        JSONObject json = new JSONObject();
        json = sysBuyInterface.subBuyModuleSelect(map.get("buyid").toString());

        return json;
    }

    @PostMapping("buyModuleUpdate")
    public JSONObject buyModuleUpdate(@RequestParam(value = "css", required = false) MultipartFile css, @RequestParam Map<String, Object> map) {
        JSONObject json = new JSONObject();
        if (css == null || css.equals("")) {
            map.put("css", null);
        } else {
            map.put("css", url + "/" + new ImgUtilTg().FileImg(css, 200, 200));
        }
        json = sysBuyInterface.buyModuleUpdate(map);
        return json;
    }

    @PostMapping("buyUpdateDel")
    public JSONObject buyUpdateDel(@RequestParam("pid[]") String[] pid, @RequestParam Map<String, Object> map) {
        JSONObject json = new JSONObject();
        if (map.get("style").equals("4")) {
            map.put("state", "0");
        } else if (map.get("style").equals("5")) {
            map.put("state", "1");
        } else {
            map.put("isdel", "1");
        }
        for (String id : pid) {
            map.put("pid", id);
            json = sysBuyInterface.buyUpdateDel(map);

        }
        return json;
    }


}
