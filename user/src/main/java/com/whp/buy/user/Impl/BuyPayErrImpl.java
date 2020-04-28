package com.whp.buy.user.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Dao.BuyPayErrDao;
import com.whp.buy.user.Interface.BuyPayErrInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BuyPayErrImpl implements BuyPayErrInterface {
    @Autowired
    private BuyPayErrDao buyPayErrDao;

    @Override
    public JSONObject buyPayErrSelect(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = buyPayErrDao.buyPayErrSelectByPage(map, Integer.parseInt(map.get("start").toString()), Integer.parseInt(map.get("end").toString()));
            int count = buyPayErrDao.buyPayErrSelectByCount(map);
            json.put("code", 100);
            json.put("errList", list);
            json.put("count", count);


        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }

    @Override
    public JSONObject buyPayErrSelectByCount(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            int count = buyPayErrDao.buyPayErrSelectByCount(map);
            json.put("code", 100);
            json.put("count", count);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }
}
