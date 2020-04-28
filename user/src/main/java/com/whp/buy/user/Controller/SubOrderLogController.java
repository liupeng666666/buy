package com.whp.buy.user.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Interface.SubOrderLogInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/subOrderLog")
public class SubOrderLogController {
    @Autowired
    private SubOrderLogInterface subOrderLogInterface;

    @PostMapping("subOrderLogSelectById")
    public JSONObject subOrderLogSelectById(@RequestParam Map<String, Object> map) {
        JSONObject json = subOrderLogInterface.subOrderLogSelectById(map);
        return json;
    }
}
