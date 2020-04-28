package com.whp.buy.user.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Interface.SubUserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/subUser")
public class SubUserController {
    @Autowired
    private SubUserInterface subUserInterface;

    @PostMapping("subUserSelect")
    public JSONObject subUserSelect(@RequestParam Map<String, Object> map) {
        JSONObject json = subUserInterface.subUserSelect(map);
        return json;
    }

    @PostMapping("subUserUpdateDel")
    public JSONObject subUserUpdateDel(@RequestParam("pid[]") String[] pid, @RequestParam Map<String, Object> map) {
        JSONObject json = new JSONObject();
        for (String id : pid) {
            map.put("pid", id);
            json = subUserInterface.subUserUpdateDel(map);
        }
        return json;
    }

    @PostMapping("subUserSelectByNum")
    public JSONObject subUserSelectByNum(@RequestParam Map<String, Object> map) {
        JSONObject json = subUserInterface.subUserSelectByNum(map);
        return json;
    }

}
