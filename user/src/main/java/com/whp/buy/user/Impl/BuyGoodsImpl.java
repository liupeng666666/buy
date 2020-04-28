package com.whp.buy.user.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Dao.BuyGoodsDao;
import com.whp.buy.user.Interface.BuyGoodsInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BuyGoodsImpl implements BuyGoodsInterface {
    @Autowired
    private BuyGoodsDao buyGoodsDao;

    @Override
    public JSONObject buyGoodsSelect(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        Map<String, Object> map2 = new HashMap<>();
        try {
            List<Map<String, Object>> list = buyGoodsDao.buyGoodsSelectByPage(map, Integer.parseInt(map.get("start").toString()), Integer.parseInt(map.get("end").toString()));
            int count = buyGoodsDao.buyGoodsSelectByCount(map);
            for (Map map1 : list) {
                JSONObject jsonObject = JSON.parseObject(map1.get("img").toString());
                String pic = jsonObject.getString("thumbnail");
                map1.put("image", pic);
            }
            json.put("count", count);
            json.put("goodsList", list);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
            json.put("message", "连接数据库失败，无法获取相关数据");
        }

        return json;
    }
}
