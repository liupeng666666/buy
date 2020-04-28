package com.whp.buy.user.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Dao.BuyRoleDao;
import com.whp.buy.user.Dao.BuyUserDao;
import com.whp.buy.user.Interface.BuyRoleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.List;
import java.util.Map;

@Service
public class BuyRoleImpl implements BuyRoleInterface {
    @Autowired
    private BuyRoleDao buyRoleDao;
    @Autowired
    private BuyUserDao buyUserDao;

    @Override
    public JSONObject buyRoleSelect(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = buyRoleDao.buyRoleSelectByPage(map, Integer.parseInt(map.get("start").toString()), Integer.parseInt(map.get("end").toString()));
            int count = buyRoleDao.buyRoleSelectByCount(map);
            json.put("code", 100);
            json.put("roleList", list);
            json.put("count", count);


        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }

    @Override
    public JSONObject buyRoleDelUpdate(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            buyRoleDao.buyRoleDelUpdate(map);
            buyRoleDao.buyRoleModuleDelById(map.get("pid").toString());
            buyUserDao.buyUserUpdateDelByRoleId(map.get("pid").toString());
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }
}
