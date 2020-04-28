package com.whp.buy.user.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface BuyRoleInterface {
    JSONObject buyRoleSelect(Map<String, Object> map);

    JSONObject buyRoleDelUpdate(Map<String, Object> map);
}
