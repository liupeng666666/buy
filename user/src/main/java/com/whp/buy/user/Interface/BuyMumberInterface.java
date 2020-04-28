package com.whp.buy.user.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface BuyMumberInterface {
    JSONObject buyMumberSelect(Map<String, Object> map);

    JSONObject buyMumberDelUpdate(Map<String, Object> map);

    JSONObject buyMumberSelectByBuyId(Map<String, Object> map);
}
