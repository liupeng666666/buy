package com.whp.buy.user.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysRoleDao {
    int sysRoleAdd(Map<String, Object> map);

    int sysRoleUpdate(Map<String, Object> map);

    void sysRoleDel(@Param("roleid") String roleid);

    List<Map<String, Object>> sysRoleSelectBydept(List<String> departmentcode);

    List<Map<String, Object>> getRoleByUser(@Param("userid") String userid);

    void sysUserRoleDel(@Param("userid") String userid, @Param("roleid") String roleid);

    void sysUserRoleAdd(@Param("userid") String userid, @Param("roleid") String roleid);

}
