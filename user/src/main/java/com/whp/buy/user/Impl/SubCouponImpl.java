package com.whp.buy.user.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Dao.SubCouponDao;
import com.whp.buy.user.Interface.SubCouponInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SubCouponImpl implements SubCouponInterface {
    @Autowired
    private SubCouponDao subCouponDao;

    @Override
    public JSONObject subCouponSelect(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = subCouponDao.subCouponSelectByPage(map, Integer.parseInt(map.get("start").toString()), Integer.parseInt(map.get("end").toString()));
            int count = subCouponDao.subCouponSelectByCount(map);
            json.put("code", 100);
            json.put("couponList", list);
            json.put("count", count);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }

    @Override
    public JSONObject subuCouponSelect(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = subCouponDao.subuCouponSelectByPage(map, Integer.parseInt(map.get("start").toString()), Integer.parseInt(map.get("end").toString()));
            int count = subCouponDao.subuCouponSelectByCount(map);
            json.put("code", 100);
            json.put("couponList", list);
            json.put("count", count);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }
}
