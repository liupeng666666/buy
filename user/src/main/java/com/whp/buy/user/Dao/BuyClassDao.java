package com.whp.buy.user.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BuyClassDao {
    List<Map<String, Object>> buyClassSelectByPage(@Param("map") Map<String, Object> map, @Param("start") int start, @Param("end") int end);

    int buyClassSelectByCount(@Param("map") Map<String, Object> map);

    List<Map<String, Object>> buyClassSelectBySearch(@Param("map") Map<String, Object> map);
}
