package com.whp.buy.user.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface SubBuyInterface {
    JSONObject subBuyAdd(Map<String, Object> map);

    JSONObject subBuySelect(Map<String, Object> map);

    JSONObject subBuyUpdate(Map<String, Object> map);

    JSONObject subBuyDelUpdate(Map<String, Object> map);

    JSONObject subBuySelectsub();

    JSONObject subBuySelectByNum(Map<String, Object> map);
}
