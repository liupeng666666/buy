package com.whp.buy.user.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface BuyClassInterface {
    JSONObject buyClassSelect(Map<String, Object> map);

    JSONObject buyClassSelectBySearch(Map<String, Object> map);
}
