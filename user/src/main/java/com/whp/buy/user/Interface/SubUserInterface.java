package com.whp.buy.user.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface SubUserInterface {
    JSONObject subUserSelect(Map<String, Object> map);

    JSONObject subUserUpdateDel(Map<String, Object> map);

    JSONObject subUserSelectByNum(Map<String, Object> map);
}
