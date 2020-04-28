package com.whp.buy.user.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SubIntegralDao {
    List<Map<String, Object>> subIntegralSelectByPage(@Param("map") Map<String, Object> map, @Param("start") int start, @Param("end") int end);

    int subIntegralSelectByCount(@Param("map") Map<String, Object> map);

    List<Map<String, Object>> subuIntegralSelectByPage(@Param("map") Map<String, Object> map, @Param("start") int start, @Param("end") int end);

    int subuIntegralSelectByCount(@Param("map") Map<String, Object> map);
}
