package com.whp.buy.user.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysDepartmentDao {
    List<Map<String, Object>> getDepartment(@Param("parentdepartmentcode") String parentdepartmentcode);

    int departmentAdd(Map<String, Object> map);

    int departmentUpdate(Map<String, Object> map);

    void departmentDel(@Param("departmentcode") String departmentcode);//删除组织机构

    void roleDel(@Param("departmentcode") String departmentcode);//删除该组织机构下的角色

    void sysUserDel(@Param("departmentcode") String departmentcode);//删除该组织机构下的用户

    Map<String, Object> getParentBydepartmentcode(@Param("departmentcode") String departmentcode);

}
