package com.whp.buy.user.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Dao.SysDepartmentDao;
import com.whp.buy.user.Dao.SysUserDao;
import com.whp.buy.user.Interface.SysUserInterface;
import com.whp.buy.utils.util.MD5Util;
import com.whp.buy.utils.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/8/7 17:21
 * @descrpition :
 */
@Service
public class SysUserImpl implements SysUserInterface {
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysDepartmentDao sysDepartmentDao;

    @Override
    public JSONObject getSubUserByUser(String pid) {
        JSONObject json = new JSONObject();
        try {
            String value = RedisUtils.get("login." + pid, 2);
            if (value == null || value == "") {
                Map<String, Object> map = sysUserDao.getSubUserByUser(pid);
                json.put("user", map);
            } else {
                json.put("user", JSONObject.parse(value));
            }
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }

        return json;
    }

    @Override
    public JSONObject getSubUserLogin(String username, String password) {
        JSONObject json = new JSONObject();
        try {
            if (username != null && !"".equals(username) && password != null && !"".equals(password)) {
                password = MD5Util.MD5(password);
                Map<String, Object> map = sysUserDao.GetSubUserLogin(username, password);
                if (map != null) {
                    RedisUtils.set("login." + map.get("userid"), new JSONObject(map).toString(), 2);
                    json.put("userid", map.get("userid"));
                    json.put("departmentcode", map.get("departmentcode").toString());
                    Map<String, Object> fmap = sysUserDao.getSysUserModalSelectDan(map.get("userid").toString());
                    json.put("modal", fmap);
                    json.put("code", 100);
                } else {
                    json.put("code", 102);
                }
            } else {
                json.put("code", 101);
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject getSysUserModalSelect(String userid) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = sysUserDao.getSysUserModalSelect(userid);
            JSONObject fjson = getSubUserByUser(userid);
            json.put("user", fjson.get("user"));
            json.put("modal", list);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }

        return json;
    }

    @Override
    public JSONObject sysUserAdd(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            if (map.containsKey("username") && map.get("username") != null && map.containsKey("password") && map.get("password") != null && map.containsKey("departmentcode") && map.get("departmentcode") != null && map.containsKey("createuserid") && map.get("createuserid") != null && map.containsKey("lastedituserid") && map.get("lastedituserid") != null) {
                int count = sysUserDao.isSysUser(map);
                if (count == 0) {
                    String pwd = MD5Util.MD5(map.get("password").toString());
                    map.put("password", pwd);
                    int number = sysUserDao.sysUserAdd(map);
                    if (number > 0) {
                        json.put("code", "100");
                    } else {
                        json.put("code", "103");
                        json.put("message", "连接数据库失败，无法添加相关数据");
                    }
                } else {
                    json.put("code", 104);
                    json.put("message", "用户已存在");
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
    public JSONObject getSysUserSelect(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = new ArrayList<>();
            List<String> stlist = new ArrayList<>();
            List<Map<String, Object>> clist = new ArrayList<>();
            clist = digui(clist, map.get("departmentcode").toString());
            for (Map<String, Object> map1 : clist) {
                String depart = map1.get("departmentcode").toString();
                stlist.add(depart);
            }
            if (stlist != null && !"".equals(stlist)) {
                list = sysUserDao.sysUserByPage(stlist, Integer.parseInt(map.get("start").toString()), Integer.parseInt(map.get("end").toString()));
                int count = sysUserDao.sysUserCount(stlist);
                json.put("code", 100);
                json.put("userList", list);
                json.put("count", count);
                json.put("departcode", map.get("departmentcode").toString());
            } else {
                json.put("code", 102);
                json.put("message", "请求数据语法错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }

    @Override
    public JSONObject getSysUserBySearch(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> users = new ArrayList<>();
            List<String> stlist = new ArrayList<>();
            List<Map<String, Object>> clist = new ArrayList<>();
            String code = map.get("code").toString();
            String username = map.get("username").toString();
            if (code == "" || code == null) {
                clist = digui(clist, map.get("departmentcode").toString());
                for (Map<String, Object> map1 : clist) {
                    String depart = map1.get("departmentcode").toString();
                    stlist.add(depart);
                }
            } else {
                stlist.add(code);
            }
            users = sysUserDao.sysUserBySearchPage(stlist, username, Integer.parseInt(map.get("start").toString()), Integer.parseInt(map.get("end").toString()));
            int count = sysUserDao.sysUserBySearchCount(stlist, username);
            json.put("code", 100);
            json.put("users", users);
            json.put("count", count);

        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", "103");
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }

//    @Override
//    public JSONObject sysUserById(String userid) {
//        JSONObject json = new JSONObject();
//        try {
//            List<Map<String,Object>> userMap = sysUserDao.sysUserById(userid);
//            json.put("userMap", userMap);
//            json.put("code", 100);
//        } catch (Exception e) {
//            e.printStackTrace();
//            json.put("code", 103);
//        }
//
//        return json;
//    }

    @Override
    public JSONObject sysUserUpdate(Map<String, String> map) {
        JSONObject json = new JSONObject();
        try {
            if (map.containsKey("userid") && map.get("userid") != null) {
                int number = sysUserDao.sysUserUpdate(map);
                if (number > 0) {
                    json.put("code", 100);
                } else {
                    json.put("code", 103);
                    json.put("message", "连接数据库失败，无法修改相关数据");
                }

            } else {
                json.put("code", 102);
                json.put("message", "请求数据语法错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
            json.put("message", "连接数据库失败，无法修改相关数据");
        }

        return json;
    }


    //递归查询登录人之下的所有组织结构
    public List<Map<String, Object>> digui(List<Map<String, Object>> clist, String departmentcode) {
        List<Map<String, Object>> list = sysDepartmentDao.getDepartment(departmentcode);
        for (Map<String, Object> cmap : list) {
            clist.add(cmap);
            digui(clist, cmap.get("departmentcode").toString());
        }
        return clist;
    }


}