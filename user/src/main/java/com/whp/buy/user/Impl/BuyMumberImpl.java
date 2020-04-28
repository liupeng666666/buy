package com.whp.buy.user.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Dao.BuyMumberDao;
import com.whp.buy.user.Interface.BuyMumberInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class BuyMumberImpl implements BuyMumberInterface {
    @Autowired
    private BuyMumberDao buyMumberDao;

    @Override
    public JSONObject buyMumberSelect(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = buyMumberDao.buyMumberSelectByPage(map, Integer.parseInt(map.get("start").toString()), Integer.parseInt(map.get("end").toString()));
            int count = buyMumberDao.buyMumberSelectByCount(map);
            json.put("code", 100);
            json.put("mumberList", list);
            json.put("count", count);


        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }

    @Override
    public JSONObject buyMumberDelUpdate(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            buyMumberDao.buyMumberDelUpdate(map);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }

    @Override
    public JSONObject buyMumberSelectByBuyId(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = buyMumberDao.buyMumberSelectByBuyId(map);

            json.put("code", 100);
            json.put("mumberList", list);


        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }

}
