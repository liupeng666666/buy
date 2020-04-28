package com.whp.buy.user.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface BuyPayErrInterface {
    JSONObject buyPayErrSelect(Map<String, Object> map);

    JSONObject buyPayErrSelectByCount(Map<String, Object> map);
}
