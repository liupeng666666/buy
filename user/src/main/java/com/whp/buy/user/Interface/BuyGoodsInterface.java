package com.whp.buy.user.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface BuyGoodsInterface {
    JSONObject buyGoodsSelect(Map<String, Object> map);

}
