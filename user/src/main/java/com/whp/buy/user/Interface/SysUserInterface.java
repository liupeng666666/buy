package com.whp.buy.user.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/8/7 17:20
 * @descrpition :
 */
public interface SysUserInterface {

    public JSONObject getSubUserByUser(String pid);

    public JSONObject getSubUserLogin(String username, String password);

    public JSONObject getSysUserModalSelect(String userid);

    JSONObject sysUserAdd(Map<String, Object> map);

    JSONObject getSysUserSelect(Map<String, Object> map);

    JSONObject getSysUserBySearch(Map<String, Object> map);

    //    JSONObject sysUserById(String userid);
    JSONObject sysUserUpdate(Map<String, String> map);
}
