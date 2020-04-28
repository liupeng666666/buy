package com.whp.buy.user.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Dao.BuyPayDao;
import com.whp.buy.user.Dao.SubBuyDao;
import com.whp.buy.user.Interface.BuyPayInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BuyPayImpl implements BuyPayInterface {
    @Autowired
    private BuyPayDao buyPayDao;
    @Autowired
    private SubBuyDao subBuyDao;

    @Override
    public JSONObject buyPaySelect(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = buyPayDao.buyPaySelectByPage(map, Integer.parseInt(map.get("start").toString()), Integer.parseInt(map.get("end").toString()));
            int count = buyPayDao.buyPaySelectByCount(map);
            json.put("code", 100);
            json.put("payList", list);
            json.put("count", count);


        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }

    @Override
    public JSONObject buyPayAdd(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            int number = buyPayDao.buyPayAdd(map);
            if (number > 0) {
                subBuyDao.subBuyUpdateById(map.get("payid").toString(), map.get("buyid").toString());
                json.put("code", 100);
            } else {
                json.put("code", 103);
                json.put("message", "连接数据库失败，无法添加相关数据");
            }

        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }

    @Override
    public JSONObject buyPayUpdate(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            int number = buyPayDao.buyPayUpdate(map);
            if (number > 0) {
                json.put("code", 100);
            } else {
                json.put("code", 103);
                json.put("message", "连接数据库失败，无法添加相关数据");
            }

        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }
}
