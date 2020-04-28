package com.whp.buy.user.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysClassDao {
    int sysClassAdd(Map<String, Object> map);

    List<Map<String, Object>> sysClassSelectByPage(@Param("map") Map<String, Object> map, @Param("start") int start, @Param("end") int end);

    int sysClassSelectByCount(@Param("map") Map<String, Object> map);

    int sysClassUpdate(Map<String, Object> map);

    int sysClassDelUpdate(Map<String, Object> map);
}
