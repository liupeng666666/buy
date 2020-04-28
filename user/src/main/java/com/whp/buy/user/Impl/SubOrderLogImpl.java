package com.whp.buy.user.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Dao.SubOrderLogDao;
import com.whp.buy.user.Interface.SubOrderLogInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SubOrderLogImpl implements SubOrderLogInterface {
    @Autowired
    private SubOrderLogDao subOrderLogDao;

    @Override
    public JSONObject subOrderLogSelectById(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = subOrderLogDao.subOrderLogSelectById(map);
            json.put("code", 100);
            json.put("logList", list);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }
}
