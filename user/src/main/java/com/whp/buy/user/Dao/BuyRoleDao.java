package com.whp.buy.user.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BuyRoleDao {
    int buyRoleAdd(Map<String, Object> map);

    int buyRoleModuleAdd(Map<String, Object> map);

    int buyRoleModuleDel(@Param("buyid") String buyid, @Param("moduleid") String moduleid);

    List<Map<String, Object>> buyRoleSelectByBuyid(String buyid);

    List<Map<String, Object>> buyRoleSelectByPage(@Param("map") Map<String, Object> map, @Param("start") int start, @Param("end") int end);

    int buyRoleSelectByCount(@Param("map") Map<String, Object> map);

    int buyRoleDelUpdate(Map<String, Object> map);

    int buyRoleUpdateDel(@Param("buyid") String buyid);

    int buyRoleModuleUpdateDel(@Param("buyid") String buyid);

    int buyRoleModuleDelById(String roleid);
}
