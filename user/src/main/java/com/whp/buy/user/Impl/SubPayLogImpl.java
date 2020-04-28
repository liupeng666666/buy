package com.whp.buy.user.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Dao.SubPayLogDao;
import com.whp.buy.user.Interface.SubPayLogInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SubPayLogImpl implements SubPayLogInterface {
    @Autowired
    private SubPayLogDao subPayLogDao;

    @Override
    public JSONObject subPayLogSelectByTotal(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            Map<String, Object> payMap = new HashMap<>();
            String payAll = subPayLogDao.subPayLogSelectByTotalAll(map);
            String payLast = subPayLogDao.subPayLogSelectByTotalLast(map);
            String payNow = subPayLogDao.subPayLogSelectByTotalNow(map);

            payMap.put("payAll", payAll);
            payMap.put("payLast", payLast);
            payMap.put("payNow", payNow);
            json.put("code", 100);
            json.put("payMap", payMap);

        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }
}
