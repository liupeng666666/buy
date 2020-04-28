package com.whp.buy.user.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Mapper
public interface SysBuyDao {
    void buyModuleAdd(Map<String, Object> map);

    List<Map<String, Object>> getBuyModule();

    List<Map<String, Object>> getBuyForBuy();

    int buyModuleUpdate(Map<String, Object> map);

    List<Map<String, Object>> getBuyById(@Param("pid") String pid);

    List<Map<String, Object>> getBuyByPid(@Param("parentid") String parentid);

    int buyUpdateDel(Map<String, Object> map);
}
