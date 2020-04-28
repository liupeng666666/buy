package com.whp.buy.user.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface BuyPayInterface {
    JSONObject buyPaySelect(Map<String, Object> map);

    JSONObject buyPayAdd(Map<String, Object> map);

    JSONObject buyPayUpdate(Map<String, Object> map);
}
