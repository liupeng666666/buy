package com.whp.buy.user.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Interface.SubOrderInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/subOrder")
public class SubOrderController {
    @Autowired
    private SubOrderInterface subOrderInterface;


    @PostMapping("subOrderSelect")
    public JSONObject subOrderSelect(@RequestParam Map<String, Object> map) {
        JSONObject json = subOrderInterface.subOrderSelect(map);
        return json;
    }

    @PostMapping("subOrderSelectByTotal")
    public JSONObject subOrderSelectByTotal(@RequestParam Map<String, Object> map) {
        JSONObject json = subOrderInterface.subOrderSelectByTotal(map);
        return json;
    }

    @PostMapping("subOrderSelectByMember")
    public JSONObject subOrderSelectByMember(@RequestParam Map<String, Object> map) {
        JSONObject json = subOrderInterface.subOrderSelectByMember(map);
        return json;
    }

}
