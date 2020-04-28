package com.whp.buy.user.Interface;

import com.alibaba.fastjson.JSONObject;
import org.omg.CORBA.OBJ_ADAPTER;

import java.util.Map;

public interface BuyRechargeInterface {
    JSONObject buyRechargeSelect(Map<String, Object> map);
}
