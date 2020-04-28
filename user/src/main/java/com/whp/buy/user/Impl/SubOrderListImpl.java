package com.whp.buy.user.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Dao.SubOrderListDao;
import com.whp.buy.user.Dao.SubUserDao;
import com.whp.buy.user.Interface.SubOrderListInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SubOrderListImpl implements SubOrderListInterface {
    @Autowired
    private SubOrderListDao subOrderListDao;
    @Autowired
    private SubUserDao subUserDao;

    @Override
    public JSONObject subOrderListSelect(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = subOrderListDao.subOrderListSelectByPage(map, Integer.parseInt(map.get("start").toString()), Integer.parseInt(map.get("end").toString()));
            int count = subOrderListDao.subOrderListSelectByCount(map);
            json.put("code", 100);
            json.put("orderList", list);
            json.put("count", count);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }

    @Override
    public JSONObject subOrderListSelectById(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = subOrderListDao.subOrderListSelectById(map);
            json.put("code", 100);
            json.put("list", list);

        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }
}
