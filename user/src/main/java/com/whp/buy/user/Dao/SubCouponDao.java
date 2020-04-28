package com.whp.buy.user.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SubCouponDao {
    List<Map<String, Object>> subCouponSelectByPage(@Param("map") Map<String, Object> map, @Param("start") int start, @Param("end") int end);

    int subCouponSelectByCount(@Param("map") Map<String, Object> map);

    List<Map<String, Object>> subuCouponSelectByPage(@Param("map") Map<String, Object> map, @Param("start") int start, @Param("end") int end);

    int subuCouponSelectByCount(@Param("map") Map<String, Object> map);
}
