package com.whp.buy.user.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Interface.BuyMumberInterface;
import com.whp.buy.user.Interface.BuyRoleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/BuyRole")
public class BuyRoleController {
    @Autowired
    private BuyRoleInterface buyRoleInterface;

    @PostMapping("buyRoleSelect")
    public JSONObject buyRoleSelect(@RequestParam Map<String, Object> map) {
        JSONObject json = buyRoleInterface.buyRoleSelect(map);
        return json;
    }

    @PostMapping("buyRoleDelUpdate")
    public JSONObject buyRoleDelUpdate(@RequestParam("pid[]") String[] pid, @RequestParam Map<String, Object> map) {
        JSONObject json = new JSONObject();
        for (String id : pid) {
            map.put("pid", id);
            json = buyRoleInterface.buyRoleDelUpdate(map);
        }
        return json;


    }
}
