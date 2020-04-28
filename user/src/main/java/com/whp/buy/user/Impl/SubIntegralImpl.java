package com.whp.buy.user.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Dao.SubIntegralDao;
import com.whp.buy.user.Interface.SubIntegralInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SubIntegralImpl implements SubIntegralInterface {
    @Autowired
    private SubIntegralDao subIntegralDao;

    @Override
    public JSONObject subIntegralSelect(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = subIntegralDao.subIntegralSelectByPage(map, Integer.parseInt(map.get("start").toString()), Integer.parseInt(map.get("end").toString()));
            int count = subIntegralDao.subIntegralSelectByCount(map);
            json.put("code", 100);
            json.put("integralList", list);
            json.put("count", count);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }

    @Override
    public JSONObject subuIntegralSelect(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = subIntegralDao.subuIntegralSelectByPage(map, Integer.parseInt(map.get("start").toString()), Integer.parseInt(map.get("end").toString()));
            int count = subIntegralDao.subuIntegralSelectByCount(map);
            json.put("code", 100);
            json.put("integralList", list);
            json.put("count", count);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }
}
