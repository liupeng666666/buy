package com.whp.buy.user.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BuyModuleBuyDao {
    int subBuyModuleAdd(Map<String, Object> map);

    int buyModuleBuyAdd(Map<String, Object> map);

    //根据商家id查权限
    List<Map<String, Object>> subBuyModuleSelect(String buyid);

    List<Map<String, Object>> buyModuleBuySelect(String buyid);

    int subBuyModuleDel(@Param("sys_buy_id") String buyid, @Param("buy_module_id") String moduleid);

    int buyModuleBuyDel(@Param("sys_buy_id") String buyid, @Param("buy_module_id") String moduleid);

}
