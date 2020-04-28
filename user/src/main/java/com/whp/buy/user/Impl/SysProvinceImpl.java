package com.whp.buy.user.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Dao.SysProvinceDao;
import com.whp.buy.user.Interface.SysProvinceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysProvinceImpl implements SysProvinceInterface {
    @Autowired
    private SysProvinceDao sysProvinceDao;

    @Override
    public JSONObject getProvince() {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = sysProvinceDao.getProvince();
            json.put("provinceList", list);
            json.put("code", 100);

        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }

}
