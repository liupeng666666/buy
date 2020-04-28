package com.whp.buy.user.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Dao.SysUserDao;
import com.whp.buy.user.Interface.SysRoleInterface;
import com.whp.buy.user.Interface.SysUserInterface;
import com.whp.buy.utils.util.JWTUtil;
import com.whp.buy.utils.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/Sysrole")
public class SysRoleController {
    @Autowired
    private SysRoleInterface sysRoleInterface;
    @Autowired
    private SysUserDao sysUserDao;

    @PostMapping("sysRoleAdd")
    public JSONObject sysRoleAdd(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        map.put("roleid", MD5Util.createId());
        map.put("createuserid", JWTUtil.getUsername(request.getHeader("Authorization"), "pid"));
        JSONObject json = sysRoleInterface.sysRoleAdd(map);

        return json;
    }

    @PostMapping("sysRoleSelectBydept")
    public JSONObject sysRoleSelectBydept(@RequestParam Map<String, Object> map) {
        JSONObject json = new JSONObject();
//            String userid=JWTUtil.getUsername(request.getHeader("Authorization"),"pid");
//            Map<String,Object> usermap = sysUserDao.getSubUserByUser(userid);
        json = sysRoleInterface.sysRoleSelectBydept(map.get("departmentcode").toString());


        return json;
    }

    @PostMapping("sysRoleSelectBydeptcode")
    public JSONObject sysRoleSelectBydeptcode(String departmentcode) {
        JSONObject json = sysRoleInterface.sysRoleSelectBydeptcode(departmentcode);


        return json;
    }

    @PostMapping("sysRoleUpdate")
    public JSONObject sysRoleUpdate(@RequestParam Map<String, Object> map) {
        JSONObject json = sysRoleInterface.sysRoleUpdate(map);
        return json;
    }

    @PostMapping("sysRoleDel")
    public JSONObject delDepartment(@RequestParam("roleid[]") String[] roleids, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String pid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        for (String roleid : roleids) {
            json = sysRoleInterface.sysRoleDel(roleid, pid);
        }

        return json;
    }

}
