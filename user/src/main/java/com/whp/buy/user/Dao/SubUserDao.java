package com.whp.buy.user.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Mapper
public interface SubUserDao {
    List<Map<String, Object>> subUserSelectByPage(@Param("map") Map<String, Object> map, @Param("start") int start, @Param("end") int end);

    int subUserSelectByCount(@Param("map") Map<String, Object> map);

    int subUserUpdateDel(Map<String, Object> map);

    int subUserSelectByNumAll(Map<String, Object> map);

    int subUserSelectByNumLast(Map<String, Object> map);

    int subUserSelectByNumNow(Map<String, Object> map);
}
