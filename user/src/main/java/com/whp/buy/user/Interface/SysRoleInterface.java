package com.whp.buy.user.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;


public interface SysRoleInterface {
    JSONObject sysRoleAdd(Map<String, Object> map);

    JSONObject sysRoleUpdate(Map<String, Object> map);

    JSONObject sysRoleDel(String roleid, String userid);

    JSONObject sysRoleSelectBydept(String departmentcode);

    JSONObject sysUserRoleUpdate(String userid, String[] role);

    JSONObject sysRoleSelectBydeptcode(String departmentcode);
}
