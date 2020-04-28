package com.whp.buy.user.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface SysClassInterface {
    JSONObject sysClassAdd(Map<String, Object> map);

    JSONObject sysClassSelect(Map<String, Object> map);

    JSONObject sysClassUpdate(Map<String, Object> map);

    JSONObject sysClassDelUpdate(Map<String, Object> map);
}
