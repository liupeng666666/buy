package com.whp.buy.user.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Interface.SubOrderListInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/subOrderList")
public class SubOrderListController {
    @Autowired
    private SubOrderListInterface subOrderListInterface;

    @PostMapping("subOrderListSelect")
    public JSONObject subOrderListSelect(@RequestParam Map<String, Object> map) {
        JSONObject json = subOrderListInterface.subOrderListSelect(map);
        return json;
    }

    @PostMapping("subOrderListSelectById")
    public JSONObject subOrderListSelectById(@RequestParam Map<String, Object> map) {
        JSONObject json = subOrderListInterface.subOrderListSelectById(map);
        return json;
    }
}
