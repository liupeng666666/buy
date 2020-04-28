package com.whp.buy.user.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysModuleDao {

    List<Map<String, Object>> getModuleByDepartment(@Param("departmentcode") String departmentcode);

    List<Map<String, Object>> getModuleByRole(@Param("roleid") String roleid);

    List<Map<String, Object>> getModuleByUser(@Param("userid") String userid);

    void departmentModuleDel(@Param("departmentcode") String departmentcode, @Param("moduleid") String moduleid);

    void departmentModuleAdd(@Param("departmentcode") String departmentcode, @Param("moduleid") String moduleid, @Param("pid") String pid);

    void departmentModuleDelById(String departmentcode);

    List<Map<String, Object>> getModuleByDepartmentp(@Param("parentdepartmentcode") String parentdepartmentcode);

    void roleModuleAdd(@Param("roleid") String roleid, @Param("moduleid") String moduleid);

    void roleModuleDel(@Param("roleid") String roleid, @Param("moduleid") String moduleid);

    void roleModuleDelById(@Param("roleid") String roleid);

    List<Map<String, Object>> getModule();

    int moduleAdd(Map<String, Object> map);

    int moduleUpdate(Map<String, Object> map);

    List<Map<String, Object>> getModuleById(@Param("moduleid") String moduleid);

    List<Map<String, Object>> getModuleByPid(@Param("parentmoduleid") String parentmoduleid);

    int moduleUpdateDel(Map<String, Object> map);

    void roleModuleDelByDeptCode(@Param("departmentcode") String departmentcode);
}
