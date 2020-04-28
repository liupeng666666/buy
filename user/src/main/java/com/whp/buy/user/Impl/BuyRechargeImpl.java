package com.whp.buy.user.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Dao.BuyRechargeDao;
import com.whp.buy.user.Interface.BuyRechargeInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BuyRechargeImpl implements BuyRechargeInterface {
    @Autowired
    private BuyRechargeDao buyRechargeDao;

    @Override
    public JSONObject buyRechargeSelect(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = buyRechargeDao.buyRechargeSelectByPage(map, Integer.parseInt(map.get("start").toString()), Integer.parseInt(map.get("end").toString()));
            int count = buyRechargeDao.buyRechargeSelectByCount(map);
            json.put("code", 100);
            json.put("rechargeList", list);
            json.put("count", count);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }
}
