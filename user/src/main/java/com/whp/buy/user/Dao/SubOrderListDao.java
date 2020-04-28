package com.whp.buy.user.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SubOrderListDao {
    List<Map<String, Object>> subOrderListSelectByPage(@Param("map") Map<String, Object> map, @Param("start") int start, @Param("end") int end);

    int subOrderListSelectByCount(@Param("map") Map<String, Object> map);

    List<Map<String, Object>> subOrderListSelectById(Map<String, Object> map);

}
