package com.whp.buy.user.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Interface.SubCouponInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Action;
import java.util.Map;

@RestController
@RequestMapping("/subCoupon")
public class SubCouponController {
    @Autowired
    private SubCouponInterface subCouponInterface;

    @PostMapping("subCouponSelect")
    public JSONObject subCouponSelect(@RequestParam Map<String, Object> map) {
        JSONObject json = new JSONObject();
        json = subCouponInterface.subCouponSelect(map);
        return json;
    }

    @PostMapping("subuCouponSelect")
    public JSONObject subuCouponSelect(@RequestParam Map<String, Object> map) {
        JSONObject json = new JSONObject();
        json = subCouponInterface.subuCouponSelect(map);
        return json;
    }
}
