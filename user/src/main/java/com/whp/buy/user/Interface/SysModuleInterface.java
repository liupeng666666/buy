package com.whp.buy.user.Interface;

import com.alibaba.fastjson.JSONObject;

import javax.management.ObjectName;
import javax.security.sasl.SaslServer;
import java.util.List;
import java.util.Map;

public interface SysModuleInterface {


    JSONObject getModuleByDepartmentp(String parentdepartmentcode);

    JSONObject getModuleByDepartment(String departmentcode);

    JSONObject getModuleByRole(String roleid);

    JSONObject getModuleByUser(String userid);

    JSONObject departmentModuleUpdate(String departmentcode, String[] moduleid);//修改组织机构下的权限

    JSONObject roleModuleUpdate(String roleid, String[] moduleid);//修改角色下的权限

    JSONObject getModule();

    JSONObject moduleAdd(Map<String, Object> map);

    JSONObject moduleUpdate(Map<String, Object> map);

    JSONObject sysmoduleUpdateDel(Map<String, Object> map);
    //JSONObject getModuleById(String moduleid);
}
