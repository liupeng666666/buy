package com.whp.buy.user.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BuyPayErrDao {
    List<Map<String, Object>> buyPayErrSelectByPage(@Param("map") Map<String, Object> map, @Param("start") int start, @Param("end") int end);

    int buyPayErrSelectByCount(@Param("map") Map<String, Object> map);
}
