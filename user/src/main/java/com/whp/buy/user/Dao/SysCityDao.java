package com.whp.buy.user.Dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysCityDao {
    List<Map<String, Object>> getCity(String proviceCode);
}
