package com.whp.buy.user.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BuyMumberDao {
    List<Map<String, Object>> buyMumberSelectByPage(@Param("map") Map<String, Object> map, @Param("start") int start, @Param("end") int end);

    int buyMumberSelectByCount(@Param("map") Map<String, Object> map);

    int buyMumberDelUpdate(Map<String, Object> map);

    List<Map<String, Object>> buyMumberSelectByBuyId(Map<String, Object> map);

    int buyMumberDelUpdateByBuyId(@Param("map") Map<String, Object> map);
}
