package com.whp.buy.user.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface SubOrderListInterface {
    JSONObject subOrderListSelect(Map<String, Object> map);

    JSONObject subOrderListSelectById(Map<String, Object> map);
}
