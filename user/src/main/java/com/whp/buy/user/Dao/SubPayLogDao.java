package com.whp.buy.user.Dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface SubPayLogDao {
    String subPayLogSelectByTotalAll(Map<String, Object> map);

    String subPayLogSelectByTotalLast(Map<String, Object> map);

    String subPayLogSelectByTotalNow(Map<String, Object> map);

}
