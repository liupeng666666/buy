package com.whp.buy.user.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BuyGoodsDao {
    List<Map<String, Object>> buyGoodsSelectByPage(@Param("map") Map<String, Object> map, @Param("start") int start, @Param("end") int end);

    int buyGoodsSelectByCount(@Param("map") Map<String, Object> map);
}
