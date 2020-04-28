package com.whp.buy.user.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Dao.SysDepartmentDao;
import com.whp.buy.user.Dao.SysModuleDao;
import com.whp.buy.user.Interface.SysDepartmentInterface;
import com.whp.buy.user.Interface.SysUserInterface;
import com.whp.buy.utils.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SysDepartmentImpl implements SysDepartmentInterface {
    @Autowired
    private SysDepartmentDao sysDepartmentDao;
    @Autowired
    private SysUserInterface sysUserInterface;
    @Autowired
    private SysModuleDao sysModuleDao;

    @Override
    public JSONObject getDepartment(String userid) {
        JSONObject json = new JSONObject();
        try {
            JSONObject fjson = sysUserInterface.getSubUserByUser(userid);
            if (fjson.getInteger("code") == 100) {
                JSONObject fjson1 = fjson.getJSONObject("user");
                List<Map<String, Object>> clist = new ArrayList<>();
                clist = digui(clist, fjson1.getString("departmentcode"));
                json.put("part", clist);
                json.put("code", 100);
                json.put("departmentcode", fjson1.getString("departmentcode"));
            } else {
                json.put("code", 102);
            }

        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject departmentAdd(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            if (map.containsKey("parentdepartmentcode") && map.get("parentdepartmentcode") != null && map.containsKey("departmentname") && map.get("departmentname") != null && map.containsKey("createuserid") && map.get("createuserid") != null) {
                String departmentcode = MD5Util.createId();
                map.put("departmentcode", departmentcode);
                int number = sysDepartmentDao.departmentAdd(map);
                if (number > 0) {
                    json.put("code", 100);
                } else {
                    json.put("code", "103");
                    json.put("message", "连接数据库失败，无法添加相关数据");
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
    public JSONObject departmentUpdate(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            if (map.containsKey("departmentcode") && map.get("departmentcode") != null && !map.containsKey("isuse")) {
                int number = sysDepartmentDao.departmentUpdate(map);
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
    public JSONObject departmentDel(String departmentcode, String userid) {
        JSONObject json = new JSONObject();
        try {
            if (departmentcode != null && !"".equals(departmentcode) && userid != null && !"".equals(userid)) {

                //
                sysDepartmentDao.departmentDel(departmentcode);
                sysDepartmentDao.roleDel(departmentcode);
                sysDepartmentDao.sysUserDel(departmentcode);
                sysModuleDao.departmentModuleDelById(departmentcode);
                sysModuleDao.roleModuleDelByDeptCode(departmentcode);
                json.put("code", 100);
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

    public List<Map<String, Object>> digui(List<Map<String, Object>> clist, String pid) {
        List<Map<String, Object>> list = sysDepartmentDao.getDepartment(pid);
        for (Map<String, Object> cmap : list) {
            clist.add(cmap);
            digui(clist, cmap.get("departmentcode").toString());
        }
        return clist;
    }
}
