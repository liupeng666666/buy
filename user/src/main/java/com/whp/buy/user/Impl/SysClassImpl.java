package com.whp.buy.user.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Dao.SysClassDao;
import com.whp.buy.user.Interface.SysClassInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysClassImpl implements SysClassInterface {
    @Autowired
    private SysClassDao sysClassDao;


    @Override
    public JSONObject sysClassAdd(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            sysClassDao.sysClassAdd(map);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", "103");
            json.put("message", "连接数据库失败，无法获取相关数据");
        }


        return json;
    }

    @Override
    public JSONObject sysClassSelect(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = sysClassDao.sysClassSelectByPage(map, Integer.parseInt(map.get("start").toString()), Integer.parseInt(map.get("end").toString()));
            int count = sysClassDao.sysClassSelectByCount(map);
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
    public JSONObject sysClassUpdate(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            int number = sysClassDao.sysClassUpdate(map);
            if (number > 0) {
                json.put("code", 100);
            }

        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", "103");
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }

    @Override
    public JSONObject sysClassDelUpdate(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            sysClassDao.sysClassDelUpdate(map);

            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }
}
