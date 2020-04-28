package com.whp.buy.user.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Interface.BuyRechargeInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/buyRecharge")
public class BuyRechargeController {
    @Autowired
    private BuyRechargeInterface buyRechargeInterface;

    @PostMapping("buyRechargeSelect")
    public JSONObject buyRechargeSelect(@RequestParam Map<String, Object> map) {
        JSONObject json = buyRechargeInterface.buyRechargeSelect(map);
        return json;
    }
}
