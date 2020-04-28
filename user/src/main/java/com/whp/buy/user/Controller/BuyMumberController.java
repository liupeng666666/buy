package com.whp.buy.user.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Interface.BuyMumberInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/BuyMumber")
public class BuyMumberController {
    @Autowired
    private BuyMumberInterface buyMumberInterface;

    @PostMapping("buyMumberSelect")
    public JSONObject buyMumberSelect(@RequestParam Map<String, Object> map) {
        JSONObject json = buyMumberInterface.buyMumberSelect(map);
        return json;
    }

    @PostMapping("buyMumberDelUpdate")
    public JSONObject buyMumberDelUpdate(@RequestParam("pid[]") String[] pid, @RequestParam Map<String, Object> map) {
        JSONObject json = new JSONObject();
        for (String id : pid) {
            map.put("pid", id);
            json = buyMumberInterface.buyMumberDelUpdate(map);
        }
        return json;
    }

    @PostMapping("buyMumberSelectByBuyId")
    public JSONObject buyMumberSelectByBuyId(@RequestParam Map<String, Object> map) {
        JSONObject json = buyMumberInterface.buyMumberSelectByBuyId(map);
        return json;
    }

}
