package com.whp.buy.user.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Dao.SubUserDao;
import com.whp.buy.user.Interface.SubUserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SubUserImpl implements SubUserInterface {
    @Autowired
    private SubUserDao subUserDao;

    @Override
    public JSONObject subUserSelect(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = subUserDao.subUserSelectByPage(map, Integer.parseInt(map.get("start").toString()), Integer.parseInt(map.get("end").toString()));
            int count = subUserDao.subUserSelectByCount(map);
            json.put("code", 100);
            json.put("userList", list);
            json.put("count", count);


        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }

    @Override
    public JSONObject subUserUpdateDel(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            subUserDao.subUserUpdateDel(map);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject subUserSelectByNum(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            Map<String, Object> userMap = new HashMap<>();
            int userAll = subUserDao.subUserSelectByNumAll(map);
            int userLast = subUserDao.subUserSelectByNumLast(map);
            int userNow = subUserDao.subUserSelectByNumNow(map);

            userMap.put("userAll", userAll);
            userMap.put("userLast", userLast);
            userMap.put("userNow", userNow);
            json.put("code", 100);
            json.put("userMap", userMap);

        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }

}
