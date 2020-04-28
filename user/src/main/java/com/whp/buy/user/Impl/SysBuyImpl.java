package com.whp.buy.user.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Dao.BuyModuleBuyDao;
import com.whp.buy.user.Dao.SysBuyDao;
import com.whp.buy.user.Interface.SysBuyInterface;
import com.whp.buy.utils.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysBuyImpl implements SysBuyInterface {
    @Autowired
    private SysBuyDao sysBuyDao;
    @Autowired
    private BuyModuleBuyDao buyModuleBuyDao;

    @Override
    public JSONObject buyModuleAdd(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            String pid = MD5Util.createId();
            map.put("pid", pid);
            if (map.get("top").toString().equals("") || map.get("top").toString() == null) {
                map.put("top", 0);
            }
            if (map.get("type").toString().equals("0")) {
                map.put("parentid", 0);
                map.put("level", 1);
            } else {
                map.put("determine", null);
            }
            sysBuyDao.buyModuleAdd(map);
            json.put("code", "100");


        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", "103");
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }

    @Override
    public JSONObject getBuyModule() {
        JSONObject json = new JSONObject();
        try {

            List<Map<String, Object>> buyList = sysBuyDao.getBuyModule();
            if (buyList != null && buyList.size() > 0) {
                json.put("buyList", buyList);
                json.put("code", 100);

            } else {
                json.put("code", 101);
                json.put("message", "无相关记录");
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", "103");
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }

    @Override
    public JSONObject getBuyForBuy() {
        JSONObject json = new JSONObject();
        try {

            List<Map<String, Object>> buyList = sysBuyDao.getBuyForBuy();
            if (buyList != null && buyList.size() > 0) {
                json.put("buyList", buyList);
                json.put("code", 100);

            } else {
                json.put("code", 101);
                json.put("message", "无相关记录");
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", "103");
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }

    @Override
    public JSONObject subBuyModuleSelect(String buyid) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = buyModuleBuyDao.buyModuleBuySelect(buyid);
            json.put("code", 100);
            json.put("buyModuleList", list);

        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }

    @Override
    public JSONObject buyModuleUpdate(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            if (map.containsKey("pid") && map.get("pid") != null) {
                if (map.get("top").toString().equals("") || map.get("top").toString() == null) {
                    map.put("top", 0);
                }
                if (map.get("type").toString().equals("0")) {
                    map.put("parentid", 0);
                    map.put("level", 1);
                } else {
                    map.put("determine", null);
                }
                int number = sysBuyDao.buyModuleUpdate(map);
                if (number > 0) {
                    json.put("code", "100");
                } else {
                    json.put("code", "103");
                    json.put("message", "连接数据库失败，无法修改相关数据");
                }
            } else {
                json.put("code", "102");
                json.put("message", "请求数据语法错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", "103");
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }

    @Override
    public JSONObject buyUpdateDel(Map<String, Object> map) {
        JSONObject json = new JSONObject();

        try {
            Map<String, Object> map1 = new HashMap();
            List<Map<String, Object>> plist = new ArrayList<>();
            List<Map<String, Object>> mlist = new ArrayList<>();
            List<Map<String, Object>> list = sysBuyDao.getBuyById(map.get("pid").toString());
            for (Map mmap : list) {
                plist = sysBuyDao.getBuyById(mmap.get("parentid").toString());
            }

            mlist = digui(mlist, map.get("pid").toString());
            if (map.containsKey("state")) {//启用禁用
                if (map.get("state").toString() == "0") {
                    map1.put("state", "0");
                    if (plist == null || plist.size() == 0) {
                        map1.put("pid", map.get("pid").toString());
                        sysBuyDao.buyUpdateDel(map1);
                        json.put("code", 100);
                    }
                    for (Map pmap : plist) {
                        if (pmap.get("state").toString().equals("0")) {
                            map1.put("pid", map.get("pid").toString());
                            sysBuyDao.buyUpdateDel(map1);
                            json.put("code", 100);
                        } else {
                            json.put("code", "104");
                            json.put("message", "请先开启父级模块");
                        }
                    }

                } else {
                    map1.put("state", "1");
                    map1.put("pid", map.get("pid").toString());
                    sysBuyDao.buyUpdateDel(map1);
                    if (mlist != null || mlist.size() > 0) {
                        for (Map m : mlist) {
                            map1.put("pid", m.get("pid").toString());
                            sysBuyDao.buyUpdateDel(map1);
                        }
                    }
                    json.put("code", 100);
                }

            }
            if (map.containsKey("isdel")) {//删除
                map1.put("isdel", "1");
                map1.put("pid", map.get("pid").toString());
                sysBuyDao.buyUpdateDel(map1);
                if (mlist != null || mlist.size() > 0) {
                    for (Map m : mlist) {
                        map1.put("pid", m.get("pid").toString());
                        sysBuyDao.buyUpdateDel(map1);
                    }
                }
                json.put("code", 100);
            }


        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", "103");
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }


    public List<Map<String, Object>> digui(List<Map<String, Object>> mlist, String moduleid) {
        List<Map<String, Object>> list = sysBuyDao.getBuyByPid(moduleid);
        for (Map<String, Object> cmap : list) {
            mlist.add(cmap);
            digui(mlist, cmap.get("pid").toString());
        }
        return mlist;
    }
}
