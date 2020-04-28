package com.whp.buy.user.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface SubCouponInterface {
    JSONObject subCouponSelect(Map<String, Object> map);

    JSONObject subuCouponSelect(Map<String, Object> map);
}
