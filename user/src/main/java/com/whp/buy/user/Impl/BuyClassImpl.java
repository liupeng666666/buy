package com.whp.buy.user.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Dao.BuyClassDao;
import com.whp.buy.user.Dao.SysClassDao;
import com.whp.buy.user.Interface.BuyClassInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BuyClassImpl implements BuyClassInterface {
    @Autowired
    private BuyClassDao buyClassDao;


    @Override
    public JSONObject buyClassSelect(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = buyClassDao.buyClassSelectByPage(map, Integer.parseInt(map.get("start").toString()), Integer.parseInt(map.get("end").toString()));
            int count = buyClassDao.buyClassSelectByCount(map);
            json.put("code", 100);
            json.put("classList", list);
            json.put("count", count);

        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }

    @Override
    public JSONObject buyClassSelectBySearch(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = buyClassDao.buyClassSelectBySearch(map);
            json.put("code", 100);
            json.put("classList", list);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }

}
