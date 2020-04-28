package com.whp.buy.user.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface SubPayLogInterface {
    JSONObject subPayLogSelectByTotal(Map<String, Object> map);
}
