package com.whp.buy.user.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface SubOrderInterface {
    JSONObject subOrderSelect(Map<String, Object> map);

    JSONObject subOrderSelectByTotal(Map<String, Object> map);

    JSONObject subOrderSelectByMember(Map<String, Object> map);
}
