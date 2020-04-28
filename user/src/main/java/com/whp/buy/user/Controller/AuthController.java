package com.whp.buy.user.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Interface.SysUserInterface;
import com.whp.buy.utils.util.CaptchaUtil;
import com.whp.buy.utils.util.JWTUtil;
import com.whp.buy.utils.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author : 张吉伟
 * @data : 2019/8/7 18:08
 * @descrpition :
 */
@RestController
@RequestMapping("/Auth")
public class AuthController {
    @Autowired
    private SysUserInterface sysUserInterface;

    @PostMapping("Login")
    public JSONObject Login(String username, String password, String yzm, HttpSession session, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            String yangzhengma = (String) session.getAttribute("randomString");
            if (!yangzhengma.equals(yzm.toUpperCase())) {
                json.put("code", 104);
            } else {
                json = sysUserInterface.getSubUserLogin(username, password);
                password = MD5Util.MD5(password);
                if (json.getInteger("code") == 100) {

                    String token = JWTUtil.sign(username, password, json.getString("userid"), request.getSession().getId());
                    json.put("token", token);
                    json.put("departmentcode", json.get("departmentcode").toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    /**
     * 验证码图片
     *
     * @param request
     * @param response
     */
    @RequestMapping("/SysYzm")
    @ResponseBody
    public void SysYanZhengMa(HttpServletRequest request, HttpServletResponse response) {
        try {
            CaptchaUtil.outputCaptcha(request, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
