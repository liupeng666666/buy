package com.whp.buy.user.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Interface.SysDepartmentInterface;
import com.whp.buy.utils.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/Sysdepartment")
public class SysDepartmentController {
    @Autowired
    private SysDepartmentInterface sysDepartmentInterface;

    @PostMapping("getDepartment")
    public JSONObject getDepartment(@RequestParam String parentdepartmentcode, HttpServletRequest request) {
        String pid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        JSONObject json = sysDepartmentInterface.getDepartment(pid);
        return json;
    }

    @PostMapping("departmentAdd")
    public JSONObject addDepartment(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        map.put("createuserid", JWTUtil.getUsername(request.getHeader("Authorization"), "pid"));
        json = sysDepartmentInterface.departmentAdd(map);
        return json;
    }

    @PostMapping("departmentUpdate")
    public JSONObject departmentUpdate(@RequestParam Map<String, Object> map) {
        JSONObject json = sysDepartmentInterface.departmentUpdate(map);
        return json;
    }

    @PostMapping("departmentDel")
    public JSONObject delDepartment(@RequestParam("departmentcode[]") String[] departmentcodes, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String pid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        for (String departmentcode : departmentcodes) {
            json = sysDepartmentInterface.departmentDel(departmentcode, pid);
        }

        return json;
    }
}
