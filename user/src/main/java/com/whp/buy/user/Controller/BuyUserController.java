package com.whp.buy.user.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Interface.BuyUserInterface;
import com.whp.buy.utils.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/BuyUser")
public class BuyUserController {
    @Autowired
    private BuyUserInterface buyUserInterface;

    @PostMapping("buyUserSelect")
    public JSONObject buyRoleSelect(@RequestParam Map<String, Object> map) {
        JSONObject json = buyUserInterface.buyUserSelect(map);
        return json;
    }

    @PostMapping("buyUserDelUpdate")
    public JSONObject buyUserDelUpdate(@RequestParam("pid[]") String[] pid, @RequestParam Map<String, Object> map) {
        JSONObject json = new JSONObject();
        if (map.containsKey("password")) {
            map.put("password", MD5Util.MD5(map.get("password").toString()));
        }
        for (String id : pid) {
            map.put("pid", id);
            json = buyUserInterface.buyUserDelUpdate(map);
        }
        return json;
    }

}
