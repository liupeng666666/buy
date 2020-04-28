package com.whp.buy.user.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Interface.SysCityInterface;
import com.whp.buy.user.Interface.SysProvinceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/Sysprovince")
public class SysProvinceController {
    @Autowired
    private SysProvinceInterface sysProvinceInterface;
    @Autowired
    private SysCityInterface sysCityInterface;

    @PostMapping("getProvince")
    public JSONObject getProvince() {
        JSONObject json = sysProvinceInterface.getProvince();
        return json;
    }

    @PostMapping("getCityByProvinceCode")
    public JSONObject getCityByProvinceCode(@RequestParam Map<String, Object> map) {
        JSONObject json = sysCityInterface.getCity(map.get("provinceCode").toString());
        return json;
    }
}
