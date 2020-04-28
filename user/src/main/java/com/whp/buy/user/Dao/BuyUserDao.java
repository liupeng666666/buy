package com.whp.buy.user.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BuyUserDao {
    int buyUserAdd(Map<String, Object> map);

    int buyUserUpdateDel(String buyid);

    List<Map<String, Object>> buyUserSelectByPage(@Param("map") Map<String, Object> map, @Param("start") int start, @Param("end") int end);

    int buyUserSelectByCount(@Param("map") Map<String, Object> map);

    int buyUserDelUpdate(Map<String, Object> map);

    int buyUserUpdateDelByRoleId(String roleid);
}
