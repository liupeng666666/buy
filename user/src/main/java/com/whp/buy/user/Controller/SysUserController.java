package com.whp.buy.user.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Dao.SysUserDao;
import com.whp.buy.user.Interface.SysRoleInterface;
import com.whp.buy.user.Interface.SysUserInterface;
import com.whp.buy.utils.util.ImgUtil;
import com.whp.buy.utils.util.ImgUtilTg;
import com.whp.buy.utils.util.JWTUtil;
import com.whp.buy.utils.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/Sysuser")
public class SysUserController {
    @Autowired
    private SysUserInterface sysUserInterface;
    @Autowired
    private SysRoleInterface sysRoleInterface;
    @Value("${fast.url}")
    private String url;

    @PostMapping("SysUserAdd")
    public JSONObject addSysUser(@RequestParam(value = "img", required = false) MultipartFile img, @RequestParam("roleid[]") String[] roleid, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String userid = MD5Util.createId();
        map.put("userid", userid);
        if (img == null || img.equals("")) {
            map.put("img", null);
        } else {
            map.put("img", url + "/" + new ImgUtilTg().FileImg(img, 200, 200));
        }
        map.put("createuserid", JWTUtil.getUsername(request.getHeader("Authorization"), "pid"));
        map.put("lastedituserid", JWTUtil.getUsername(request.getHeader("Authorization"), "pid"));
        json = sysUserInterface.sysUserAdd(map);
        if (json.getInteger("code") == 100) {
            json = sysRoleInterface.sysUserRoleUpdate(userid, roleid);
        }
        return json;
    }

    @PostMapping("getSysUserSelect")
    public JSONObject getSysUserSelect(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
//        String userid=JWTUtil.getUsername(request.getHeader("Authorization"),"pid");
//        Map<String,Object> usermap = sysUserDao.getSubUserByUser(userid);
//        map.put("departmentcode",usermap.get("departmentcode").toString());
        json = sysUserInterface.getSysUserSelect(map);
        return json;
    }

    @PostMapping("getSysUserBySearch")
    public JSONObject getSysUserBySearch(@RequestParam Map<String, Object> map) {
        JSONObject json = sysUserInterface.getSysUserBySearch(map);
        return json;
    }

    //    @PostMapping("sysUserById")
//    public JSONObject sysUserById(String userid){
//        JSONObject json = sysUserInterface.sysUserById(userid);
//        return json;
//    }
    @PostMapping("sysUserUpdate")
    public JSONObject sysUserUpdate(@RequestParam(value = "img", required = false) MultipartFile img, @RequestParam(value = "roleid[]", required = false) String[] roleid, @RequestParam Map<String, String> map) {
        JSONObject json = new JSONObject();
        if (img == null || img.equals("")) {
            map.put("img", null);
        } else {
            map.put("img", url + "/" + new ImgUtilTg().FileImg(img, 200, 200));
        }
        if (map.containsKey("password")) {
            String pwd = MD5Util.MD5(map.get("password").toString());
            map.put("password", pwd);
        }
        json = sysUserInterface.sysUserUpdate(map);
        System.out.println(json.getInteger("code"));
        if (!map.containsKey("password")) {
            if (json.getInteger("code") == 100 && map.containsKey("roleid")) {
                json = sysRoleInterface.sysUserRoleUpdate(map.get("userid"), roleid);
            }
        }
        return json;
    }

    @PostMapping("sysUserUpdateDel")
    public JSONObject updateSysUser(@RequestParam("userid[]") String[] userid, @RequestParam("roleid[]") String[] roleid, @RequestParam Map<String, String> map) {
        JSONObject json = new JSONObject();
        if (map.get("style").equals("4")) {
            map.put("state", "0");
        } else if (map.get("style").equals("5")) {
            map.put("state", "1");
        } else {
            map.put("isuse", "1");
        }
        for (String user : userid) {
            map.put("userid", user);
            json = sysUserInterface.sysUserUpdate(map);
            if (!map.containsKey("state")) {
                if (json.getInteger("code") == 100) {
                    json = sysRoleInterface.sysUserRoleUpdate(map.get("userid"), roleid);
                }
            }
        }
        return json;
    }
}
