package com.whp.buy.user.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Dao.SubOrderDao;
import com.whp.buy.user.Interface.SubOrderInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SubOrderImpl implements SubOrderInterface {
    @Autowired
    private SubOrderDao subOrderDao;

    @Override
    public JSONObject subOrderSelect(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = subOrderDao.subOrderSelectByPage(map, Integer.parseInt(map.get("start").toString()), Integer.parseInt(map.get("end").toString()));
            int count = subOrderDao.subOrderSelectByCount(map);
            json.put("orderList", list);
            json.put("count", count);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
            json.put("message", "连接数据库失败，无法获取相关数据");
        }

        return json;
    }

    @Override
    public JSONObject subOrderSelectByTotal(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            Map<String, Object> orderMap = new HashMap<>();
            String totalAll = subOrderDao.subOrderSelectByTotalAll(map);
            String totalLast = subOrderDao.subOrderSelectByTotalLast(map);
            String totalNow = subOrderDao.subOrderSelectByTotalNow(map);
            orderMap.put("totalAll", totalAll);
            orderMap.put("totalLast", totalLast);
            orderMap.put("totalNow", totalNow);
            json.put("orderMap", orderMap);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
            json.put("message", "连接数据库失败，无法获取相关数据");
        }

        return json;
    }

    @Override
    public JSONObject subOrderSelectByMember(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = subOrderDao.subOrderSelectByMember(map);
            json.put("orderList", list);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
            json.put("message", "连接数据库失败，无法获取相关数据");
        }

        return json;
    }
}
