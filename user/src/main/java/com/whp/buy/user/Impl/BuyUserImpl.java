package com.whp.buy.user.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Dao.BuyUserDao;
import com.whp.buy.user.Interface.BuyUserInterface;
import com.whp.buy.utils.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BuyUserImpl implements BuyUserInterface {
    @Autowired
    private BuyUserDao buyUserDao;

    @Override
    public JSONObject buyUserSelect(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = buyUserDao.buyUserSelectByPage(map, Integer.parseInt(map.get("start").toString()), Integer.parseInt(map.get("end").toString()));
            int count = buyUserDao.buyUserSelectByCount(map);
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
    public JSONObject buyUserDelUpdate(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            buyUserDao.buyUserDelUpdate(map);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }


}
