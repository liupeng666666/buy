package com.whp.buy.user.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface SysBuyInterface {
    JSONObject buyModuleAdd(Map<String, Object> map);

    JSONObject getBuyModule();

    JSONObject getBuyForBuy();

    JSONObject subBuyModuleSelect(String buuyid);

    JSONObject buyModuleUpdate(Map<String, Object> map);

    JSONObject buyUpdateDel(Map<String, Object> map);

}
