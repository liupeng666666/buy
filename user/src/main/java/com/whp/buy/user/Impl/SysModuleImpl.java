package com.whp.buy.user.Impl;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Dao.SysDepartmentDao;
import com.whp.buy.user.Dao.SysModuleDao;
import com.whp.buy.user.Interface.SysModuleInterface;
import com.whp.buy.utils.util.MD5Util;
import com.whp.buy.utils.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysModuleImpl implements SysModuleInterface {
    @Autowired
    private SysModuleDao sysModuleDao;
    @Autowired
    private SysDepartmentDao sysDepartmentDao;

    @Override
    public JSONObject getModuleByDepartmentp(String parentdepartmentcode) {
        JSONObject json = new JSONObject();
        try {
            if (parentdepartmentcode != null && !"".equals(parentdepartmentcode)) {
                List<Map<String, Object>> list = sysModuleDao.getModuleByDepartmentp(parentdepartmentcode);
                if (list != null && list.size() > 0) {
                    json.put("code", 100);
                    json.put("module", list);
                } else {
                    json.put("code", 101);
                    json.put("message", "无相关数据!");
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
    public JSONObject getModuleByDepartment(String departmentcode) {
        JSONObject json = new JSONObject();
        try {
            if (departmentcode != null && !"".equals(departmentcode)) {
                List<Map<String, Object>> list = sysModuleDao.getModuleByDepartment(departmentcode);
                if (list != null && list.size() > 0) {
                    json.put("code", 100);
                    json.put("module", list);
                } else {
                    json.put("code", 101);
                    json.put("message", "无相关数据!");
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
    public JSONObject getModuleByRole(String roleid) {
        JSONObject json = new JSONObject();
        try {
            if (roleid != null && !"".equals(roleid)) {
                List<Map<String, Object>> list = sysModuleDao.getModuleByRole(roleid);
                if (list != null && list.size() > 0) {
                    json.put("code", 100);
                    json.put("module", list);
                } else {
                    json.put("code", 101);
                    json.put("message", "无相关数据！");
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
    public JSONObject getModuleByUser(String userid) {
        JSONObject json = new JSONObject();
        try {
            if (userid != null && !"".equals(userid)) {
                List<Map<String, Object>> list = sysModuleDao.getModuleByUser(userid);
                if (list != null && list.size() > 0) {
                    json.put("code", 100);
                    json.put("module", list);
                } else {
                    json.put("code", 101);
                    json.put("message", "无相关数据！");
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
    public JSONObject departmentModuleUpdate(String departmentcode, String[] moduleid) {
        JSONObject json = new JSONObject();

        try {
            List<Map<String, Object>> list = sysModuleDao.getModuleByDepartment(departmentcode);
            List<Map<String, Object>> clist = new ArrayList<>();
            SnowFlake snowFlake = new SnowFlake(1, 1);
            clist = digui(clist, departmentcode);
            moduleDelete(departmentcode, list, moduleid);
            for (Map<String, Object> map : clist) {
                moduleDelete(map.get("departmentcode").toString(), list, moduleid);
            }

            for (int j = 0; j < moduleid.length; j++) {
                int cz = 0;
                long pid = snowFlake.nextId();
                for (int i = 0; i < list.size(); i++) {
                    if (!moduleid[j].equals("") && moduleid[j] != "") {
                        if (list.get(i).get("moduleid").toString().equals(moduleid[j])) {
                            cz++;
                        }
                    }
                }
                if (cz == 0 && (!moduleid[j].equals("") && moduleid[j] != "")) {
                    sysModuleDao.departmentModuleAdd(departmentcode, moduleid[j], String.valueOf(pid));
                }
            }
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", "103");
            json.put("message", "连接数据库失败，无法获取相关数据");
        }
        return json;
    }

    @Override
    public JSONObject roleModuleUpdate(String roleid, String[] moduleid) {
        JSONObject json = new JSONObject();
        try {
            if (roleid != null && !"".equals(roleid)) {
                List<Map<String, Object>> list = sysModuleDao.getModuleByRole(roleid);
                for (int i = 0; i < list.size(); i++) {
                    int cz = 0;
                    for (int j = 0; j < moduleid.length; j++) {
                        if (!moduleid[j].equals("") && moduleid[j] != "") {
                            if (list.get(i).get("moduleid").toString().equals(moduleid[j])) {
                                cz += 1;
                            }
                        }
                    }
                    if (cz == 0) {
                        sysModuleDao.roleModuleDel(roleid, list.get(i).get("moduleid").toString());
                    }
                }
                for (int j = 0; j < moduleid.length; j++) {
                    int cz = 0;
                    if (!moduleid[j].equals("") && moduleid[j] != "") {
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).get("moduleid").toString().equals(moduleid[j])) {
                                cz += 1;
                            }
                        }
                        if (cz == 0) {
                            sysModuleDao.roleModuleAdd(roleid, moduleid[j]);
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
    public JSONObject getModule() {
        JSONObject json = new JSONObject();
        try {

            List<Map<String, Object>> moduleList = sysModuleDao.getModule();
            if (moduleList != null && moduleList.size() > 0) {
                json.put("moduleList", moduleList);
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
    public JSONObject moduleAdd(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            if (map.containsKey("modulename") && map.get("modulename") != null && map.containsKey("parentmoduleid") && map.get("parentmoduleid") != null && map.containsKey("level") && map.get("level") != null && map.containsKey("url") && map.get("url") != null) {
                String moduleid = MD5Util.createId();
                map.put("moduleid", moduleid);
                if (map.get("top").toString().equals("") || map.get("top").toString() == null) {
                    map.put("top", null);
                }
                int number = sysModuleDao.moduleAdd(map);
                if (number > 0) {
                    json.put("code", "100");
                    json.put("moduleid", moduleid);
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
    public JSONObject moduleUpdate(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            if (map.containsKey("moduleid") && map.get("moduleid") != null) {
                int number = sysModuleDao.moduleUpdate(map);
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
    public JSONObject sysmoduleUpdateDel(Map<String, Object> map) {
        JSONObject json = new JSONObject();

        try {
            Map<String, Object> map1 = new HashMap();
            List<Map<String, Object>> plist = new ArrayList<>();
            List<Map<String, Object>> mlist = new ArrayList<>();

            List<Map<String, Object>> list = sysModuleDao.getModuleById(map.get("moduleid").toString());
            for (Map mmap : list) {
                plist = sysModuleDao.getModuleById(mmap.get("parentmoduleid").toString());
            }

            mlist = moduleDigui(mlist, map.get("moduleid").toString());
            if (map.containsKey("state")) {//启用禁用
                if (map.get("state").toString() == "0") {
                    map1.put("state", "0");
                    if (plist == null || plist.size() == 0) {
                        map1.put("moduleid", map.get("moduleid").toString());
                        sysModuleDao.moduleUpdateDel(map1);
                        json.put("code", 100);
                    }
                    for (Map pmap : plist) {
                        if (pmap.get("state").toString().equals("0")) {
                            map1.put("moduleid", map.get("moduleid").toString());
                            sysModuleDao.moduleUpdateDel(map1);
                            json.put("code", 100);
                        } else {
                            json.put("code", "104");
                            json.put("message", "请先开启父级模块");
                        }
                    }

                } else {
                    map1.put("state", "1");
                    map1.put("moduleid", map.get("moduleid").toString());
                    sysModuleDao.moduleUpdateDel(map1);
                    if (mlist != null || mlist.size() > 0) {
                        for (Map m : mlist) {
                            map1.put("moduleid", m.get("moduleid").toString());
                            sysModuleDao.moduleUpdateDel(map1);
                        }
                    }
                    json.put("code", 100);
                }

            }
            if (map.containsKey("isuse")) {//删除
                map1.put("isuse", "1");
                map1.put("moduleid", map.get("moduleid").toString());
                sysModuleDao.moduleUpdateDel(map1);
                if (mlist != null || mlist.size() > 0) {
                    for (Map m : mlist) {
                        map1.put("moduleid", m.get("moduleid").toString());
                        sysModuleDao.moduleUpdateDel(map1);
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


    public List<Map<String, Object>> digui(List<Map<String, Object>> clist, String departmentcode) {
        List<Map<String, Object>> list = sysDepartmentDao.getDepartment(departmentcode);
        for (Map<String, Object> cmap : list) {
            clist.add(cmap);
            digui(clist, cmap.get("departmentcode").toString());
        }
        return clist;
    }

    public void moduleDelete(String departmentcode, List<Map<String, Object>> list, String[] moduleid) {
        for (int i = 0; i < list.size(); i++) {
            int cz = 0;
            for (int j = 0; j < moduleid.length; j++) {
                if (!moduleid[j].equals("") && moduleid[j] != "") {
                    if (list.get(i).get("moduleid").toString().equals(moduleid[j])) {
                        cz++;
                    }
                }
            }
            if (cz == 0) {
                sysModuleDao.departmentModuleDel(departmentcode, list.get(i).get("moduleid").toString());
                //sysModuleDao.delRoleModuleByDepartment(departmentcode, list.get(i).get("moduleid").toString());
            }
        }

    }


    //递归查询登录人下所有模块
    public List<Map<String, Object>> moduleDigui(List<Map<String, Object>> mlist, String moduleid) {
        List<Map<String, Object>> list = sysModuleDao.getModuleByPid(moduleid);
        for (Map<String, Object> cmap : list) {
            mlist.add(cmap);
            moduleDigui(mlist, cmap.get("moduleid").toString());
        }
        return mlist;
    }


}
