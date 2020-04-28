package com.whp.buy.user.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Interface.SysClassInterface;
import com.whp.buy.utils.util.ImgUtilTg;
import com.whp.buy.utils.util.JWTUtil;
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
@RequestMapping("/sysClass")
public class SysClassController {
    @Autowired
    private SysClassInterface sysClassInterface;
    @Value("${fdfs.thumb-image.width}")
    private int width;
    @Value("${fdfs.thumb-image.height}")
    private int height;
    @Value("${fast.url}")
    private String url;

    @PostMapping("sysClassAdd")
    public JSONObject sysClassAdd(@RequestParam(value = "img", required = false) MultipartFile img, @RequestParam(value = "pic", required = false) MultipartFile pic, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        if (img == null || img.equals("")) {
            map.put("img", null);
        } else {
            map.put("img", url + "/" + new ImgUtilTg().FileImg(img, width, height));
        }
        if (pic != null) {
            map.put("holiday_img", url + "/" + new ImgUtilTg().FileImg(pic, width, height));
        }
        map.put("sysuserid", JWTUtil.getUsername(request.getHeader("Authorization"), "pid"));
        System.out.println("-------" + map);
        json = sysClassInterface.sysClassAdd(map);

        return json;
    }

    @PostMapping("sysClassSelect")
    public JSONObject sysClassSelect(@RequestParam Map<String, Object> map) {
        JSONObject json = new JSONObject();
        json = sysClassInterface.sysClassSelect(map);

        return json;
    }

    @PostMapping("sysClassUpdate")
    public JSONObject sysClassUpdate(@RequestParam(value = "img", required = false) MultipartFile img, @RequestParam(value = "pic", required = false) MultipartFile pic, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        if (img == null || img.equals("")) {
            map.put("img", map.get("edit_img"));
        } else {
            map.put("img", url + "/" + new ImgUtilTg().FileImg(img, width, height));
        }
        if (pic != null) {
            map.put("holiday_img", url + "/" + new ImgUtilTg().FileImg(pic, width, height));
        } else {
            map.put("holiday_img", map.get("edit_pic"));
        }
        map.put("sysuserid", JWTUtil.getUsername(request.getHeader("Authorization"), "pid"));
        json = sysClassInterface.sysClassUpdate(map);

        return json;
    }

    @PostMapping("sysClassDelUpdate")
    public JSONObject sysClassDelUpdate(@RequestParam("pid[]") String[] pid, @RequestParam Map<String, Object> map) {
        JSONObject json = new JSONObject();
        for (String id : pid) {
            map.put("pid", id);
            json = sysClassInterface.sysClassDelUpdate(map);
        }
        return json;
    }

}
