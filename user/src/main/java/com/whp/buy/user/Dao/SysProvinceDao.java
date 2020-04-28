package com.whp.buy.user.Dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysProvinceDao {
    List<Map<String, Object>> getProvince();
}
