package com.whp.buy.user.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SubBuyDao {
    int subBuyAdd(Map<String, Object> map);

    List<Map<String, Object>> subBuySelectByPage(@Param("map") Map<String, Object> map, @Param("start") int start, @Param("end") int end);

    int subBuySelectByCount(@Param("map") Map<String, Object> map);

    int subBuyUpdate(Map<String, Object> map);

    int subBuyDelUpdate(Map<String, Object> map);

    List<Map<String, Object>> subBuySelectsub();

    int subBuySelectByNumAll(Map<String, Object> map);

    int subBuySelectByNumLast(Map<String, Object> map);

    int subBuySelectByNumNow(Map<String, Object> map);

    int subBuyUpdateById(String payid, String pid);
}
