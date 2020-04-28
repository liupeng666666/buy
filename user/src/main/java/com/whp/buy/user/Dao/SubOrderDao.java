package com.whp.buy.user.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SubOrderDao {
    List<Map<String, Object>> subOrderSelectByPage(@Param("map") Map<String, Object> map, @Param("start") int start, @Param("end") int end);

    int subOrderSelectByCount(@Param("map") Map<String, Object> map);

    String subOrderSelectByTotalAll(@Param("map") Map<String, Object> map);

    String subOrderSelectByTotalLast(@Param("map") Map<String, Object> map);

    String subOrderSelectByTotalNow(@Param("map") Map<String, Object> map);

    List<Map<String, Object>> subOrderSelectByMember(@Param("map") Map<String, Object> map);
}
