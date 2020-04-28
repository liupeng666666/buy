package com.whp.buy.user.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface SubOrderLogInterface {
    JSONObject subOrderLogSelectById(Map<String, Object> map);
}
