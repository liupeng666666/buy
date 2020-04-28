package com.whp.buy.user.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Interface.BuyPayErrInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/buyPayErr")
public class BuyPayErrController {
    @Autowired
    private BuyPayErrInterface buyPayErrInterface;

    @PostMapping("buyPayErrSelect")
    public JSONObject buyPayErrSelect(@RequestParam Map<String, Object> map) {
        JSONObject json = buyPayErrInterface.buyPayErrSelect(map);
        return json;
    }

    @PostMapping("buyPayErrSelectByCount")
    public JSONObject buyPayErrSelectByCount(@RequestParam Map<String, Object> map) {
        JSONObject json = buyPayErrInterface.buyPayErrSelectByCount(map);
        return json;
    }
}
