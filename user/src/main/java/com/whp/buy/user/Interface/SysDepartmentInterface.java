package com.whp.buy.user.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface SysDepartmentInterface {
    JSONObject getDepartment(String userid);

    JSONObject departmentAdd(Map<String, Object> map);

    JSONObject departmentUpdate(Map<String, Object> map);

    JSONObject departmentDel(String departmentcode, String userid);
}
