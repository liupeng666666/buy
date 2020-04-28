package com.whp.buy.user.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Dao.SysDepartmentDao;
import com.whp.buy.user.Dao.SysModuleDao;
import com.whp.buy.user.Dao.SysRoleDao;
import com.whp.buy.user.Interface.SysRoleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleImpl implements SysRoleInterface {
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private SysDepartmentDao sysDepartmentDao;
    @Autowired
    private SysModuleDao sysModuleDao;

    @Override
    public JSONObject sysRoleAdd(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            if (map.containsKey("departmentcode") && map.get("departmentcode") != null && map.containsKey("rolename") && map.get("rolename") != null && map.containsKey("createuserid") && map.get("createuserid") != null) {
                int number = sysRoleDao.sysRoleAdd(map);
                if (number > 0) {
                    json.put("code", 100);
                } else {
                    json.put("code", 103);
                    json.put("message", "连接数据库失败，无法添加相关数据");
                }
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
    public JSONObject sysRoleUpdate(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            if (map.containsKey("roleid") && map.get("roleid") != null) {
                int number = sysRoleDao.sysRoleUpdate(map);
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
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }

    @Override
    public JSONObject sysRoleDel(String roleid, String userid) {
        JSONObject json = new JSONObject();
        try {
            if (roleid != null && !"".equals(roleid) && userid != null && !"".equals(userid)) {
                sysRoleDao.sysRoleDel(roleid);
                sysModuleDao.roleModuleDelById(roleid);
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

    @Override
    public JSONObject sysRoleSelectBydept(String departmentcode) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = new ArrayList<>();
            List<String> stlist = new ArrayList<>();
            List<Map<String, Object>> clist = new ArrayList<>();
            clist = digui(clist, departmentcode);
            for (Map<String, Object> map : clist) {
                String depart = map.get("departmentcode").toString();
                stlist.add(depart);
            }
            if (stlist != null && !"".equals(stlist)) {
                list = sysRoleDao.sysRoleSelectBydept(stlist);
                json.put("roleList", list);
                json.put("code", 100);
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
    public JSONObject sysUserRoleUpdate(String userid, String[] role) {
        JSONObject json = new JSONObject();
        try {
            if (userid != null && !"".equals(userid)) {
                List<Map<String, Object>> list = sysRoleDao.getRoleByUser(userid);
                for (int i = 0; i < list.size(); i++) {
                    int cz = 0;
                    for (int j = 0; j < role.length; j++) {
                        if (role[j] != "" && !role[j].equals("")) {
                            if (list.get(i).get("roleid").toString().equals(role[j])) {
                                cz++;
                            }
                        }
                    }
                    if (cz == 0) {
                        sysRoleDao.sysUserRoleDel(userid, list.get(i).get("roleid").toString());
                    }
                }
                for (int j = 0; j < role.length; j++) {
                    int cz = 0;
                    if (role[j] != "" && !role[j].equals("")) {
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).get("roleid").toString().equals(role[j])) {
                                cz++;
                            }
                        }
                        if (cz == 0) {
                            sysRoleDao.sysUserRoleAdd(userid, role[j]);
                        }
                    }
                }
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

    @Override
    public JSONObject sysRoleSelectBydeptcode(String departmentcode) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = new ArrayList<>();
            List<String> stlist = new ArrayList<>();
            stlist.add(departmentcode);
            if (stlist != null && !"".equals(stlist)) {
                list = sysRoleDao.sysRoleSelectBydept(stlist);
                json.put("roleList", list);
                json.put("code", 100);
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
