package com.whp.buy.user.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Dao.*;
import com.whp.buy.user.Interface.SubBuyInterface;
import com.whp.buy.utils.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SubBuyImpl implements SubBuyInterface {

    @Autowired
    private SubBuyDao subBuyDao;
    @Autowired
    private BuyRoleDao buyRoleDao;
    @Autowired
    private BuyUserDao buyUserDao;
    @Autowired
    private BuyPayDao buyPayDao;
    @Autowired
    private BuyMumberDao buyMumberDao;

    @Override
    public JSONObject subBuyAdd(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            Color color = new Color((float) Math.random() / 2 + .5f, (float) Math.random() / 2 + .5f, (float) Math.random() / 2 + .5f);
            map.put("color", "#" + Integer.toHexString(color.getRed()) + Integer.toHexString(color.getGreen()) + Integer.toHexString(color.getBlue()));
            subBuyDao.subBuyAdd(map);//添加商家
            buyRoleDao.buyRoleAdd(map);
            buyUserDao.buyUserAdd(map);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", "103");
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }

    @Override
    public JSONObject subBuySelect(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = subBuyDao.subBuySelectByPage(map, Integer.parseInt(map.get("start").toString()), Integer.parseInt(map.get("end").toString()));
            int count = subBuyDao.subBuySelectByCount(map);
            json.put("code", 100);
            json.put("buyList", list);
            json.put("count", count);

        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }

    @Override
    public JSONObject subBuyUpdate(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {

            int number = subBuyDao.subBuyUpdate(map);
            if (map.get("appid").toString() != null || map.get("appid").toString() != "") {
                buyPayDao.buyPayUpdate(map);
            }
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
    public JSONObject subBuyDelUpdate(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            subBuyDao.subBuyDelUpdate(map);
            if (map.get("isdel").toString().equals("1")) {
                buyRoleDao.buyRoleUpdateDel(map.get("pid").toString());
                buyRoleDao.buyRoleModuleUpdateDel(map.get("pid").toString());
                buyUserDao.buyUserUpdateDel(map.get("pid").toString());
                buyMumberDao.buyMumberDelUpdateByBuyId(map);
            }
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject subBuySelectsub() {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = subBuyDao.subBuySelectsub();
            json.put("buyList", list);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", "103");
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }

    @Override
    public JSONObject subBuySelectByNum(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            Map<String, Object> buyMap = new HashMap<>();
            int buyAll = subBuyDao.subBuySelectByNumAll(map);
            int buyLast = subBuyDao.subBuySelectByNumLast(map);
            int buyNow = subBuyDao.subBuySelectByNumNow(map);

            buyMap.put("buyAll", buyAll);
            buyMap.put("buyLast", buyLast);
            buyMap.put("buyNow", buyNow);
            json.put("code", 100);
            json.put("buyMap", buyMap);

        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }

}
