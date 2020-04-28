package com.whp.buy.user.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Interface.SubPayLogInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/subPayLog")
public class SubPayLogController {
    @Autowired
    private SubPayLogInterface subPayLogInterface;

    @PostMapping("subPayLogSelectByTotal")
    public JSONObject subPayLogSelectByTotal(@RequestParam Map<String, Object> map) {
        JSONObject json = subPayLogInterface.subPayLogSelectByTotal(map);
        return json;
    }
}
