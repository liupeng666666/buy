package com.whp.buy.user.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Interface.SubCouponInterface;
import com.whp.buy.user.Interface.SubIntegralInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/subIntegral")
public class SubIntegralController {
    @Autowired
    private SubIntegralInterface subIntegralInterface;

    @PostMapping("subIntegralSelect")
    public JSONObject subIntegralSelect(@RequestParam Map<String, Object> map) {
        JSONObject json = new JSONObject();
        json = subIntegralInterface.subIntegralSelect(map);
        return json;
    }

    @PostMapping("subuIntegralSelect")
    public JSONObject subuIntegralSelect(@RequestParam Map<String, Object> map) {
        JSONObject json = new JSONObject();
        json = subIntegralInterface.subuIntegralSelect(map);
        return json;
    }
}
