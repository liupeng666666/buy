package com.whp.buy.user.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Interface.SysUserInterface;
import com.whp.buy.utils.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : 张吉伟
 * @data : 2019/8/8 12:03
 * @descrpition :
 */
@RestController
@RequestMapping("SysModal")
public class SysModalController {
    @Autowired
    private SysUserInterface sysUserInterface;

    @PostMapping("SysModalSelect")
    public JSONObject SysModalSelect(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String pid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        json = sysUserInterface.getSysUserModalSelect(pid);
        return json;
    }
}
