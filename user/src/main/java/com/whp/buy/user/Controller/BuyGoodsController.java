package com.whp.buy.user.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Interface.BuyGoodsInterface;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/buyGoods")
public class BuyGoodsController {
    @Autowired
    private BuyGoodsInterface buyGoodsInterface;

    @PostMapping("buyGoodsSelect")
    public JSONObject buyGoodsSelect(@RequestParam Map<String, Object> map) {
        JSONObject json = buyGoodsInterface.buyGoodsSelect(map);
        return json;
    }
}
