package com.whp.buy.user.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface BuyUserInterface {
    JSONObject buyUserSelect(Map<String, Object> map);

    JSONObject buyUserDelUpdate(Map<String, Object> map);

}
