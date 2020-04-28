package com.whp.buy.user.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/8/7 17:19
 * @descrpition :
 */
@Mapper
public interface SysUserDao {

    public Map<String, Object> getSubUserByUser(@Param("pid") String pid);

    /**
     * 登陆验证
     *
     * @param username
     * @param password
     * @return
     */
    public Map<String, Object> GetSubUserLogin(@Param("username") String username, @Param("password") String password);

    /**
     * 获取用户对应权限
     *
     * @param userid
     * @return
     */
    public List<Map<String, Object>> getSysUserModalSelect(@Param("userid") String userid);

    public Map<String, Object> getSysUserModalSelectDan(@Param("userid") String userid);

    //
    int sysUserAdd(Map<String, Object> map);

    int isSysUser(Map<String, Object> map);//判断用户是否存在

    List<Map<String, Object>> sysUserByPage(@Param("list") List<String> departmentcode, @Param("start") int start, @Param("end") int end);

    int sysUserCount(List<String> departmentcode);

    List<Map<String, Object>> sysUserBySearchPage(@Param("list") List<String> departmentcode, @Param("username") String username, @Param("start") int start, @Param("end") int end);

    int sysUserBySearchCount(@Param("list") List<String> departmentcode, @Param("username") String username);

    //    List<Map<String,Object>> sysUserBySearchPage(@Param("map") Map<String, Object> map,@Param("start")int start,@Param("end")int end);
//    int sysUserBySearchCount(@Param("map") Map<String, Object> map);
//    List<Map<String,Object>> sysUserById(String userid);
    int sysUserUpdate(@Param("map") Map<String, String> map);
}
