package com.whp.buy.user.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BuyPayDao {
    List<Map<String, Object>> buyPaySelectByPage(@Param("map") Map<String, Object> map, @Param("start") int start, @Param("end") int end);

    int buyPaySelectByCount(@Param("map") Map<String, Object> map);

    int buyPayAdd(Map<String, Object> map);

    int buyPayUpdate(@Param("map") Map<String, Object> map);
}
