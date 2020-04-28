package com.whp.buy.user.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Dao.SubBuyDao;
import com.whp.buy.user.Interface.BuyPayErrInterface;
import com.whp.buy.user.Interface.BuyPayInterface;
import com.whp.buy.user.Interface.SubBuyInterface;
import com.whp.buy.utils.util.FileUtil;
import com.whp.buy.utils.util.JWTUtil;
import com.whp.buy.utils.util.SnowFlake;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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
@RequestMapping("buyPay")
public class BuyPayController {
    @Autowired
    private BuyPayInterface buyPayInterface;

    @Value("${file.path}")
    private String pathname;

    @PostMapping("buyPaySelect")
    public JSONObject buyPayErrSelect(@RequestParam Map<String, Object> map) {
        JSONObject json = buyPayInterface.buyPaySelect(map);
        return json;
    }

    @PostMapping("buyPayAdd")
    public JSONObject buyPayAdd(@RequestParam(value = "path", required = false) MultipartFile path, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        SnowFlake snowFlake = new SnowFlake(1, 1);
        long pid = snowFlake.nextId();
        String floderName = String.valueOf(pid);
        if (path == null || path.equals("")) {
            map.put("path", null);
        } else {
            String filepath = FileUtil.path(path, pathname, floderName);
            map.put("path", filepath);
        }
        map.put("payid", floderName);
        map.put("pid", pid);
        map.put("sys_userid", JWTUtil.getUsername(request.getHeader("Authorization"), "pid"));
        json = buyPayInterface.buyPayAdd(map);

        return json;
    }

    @PostMapping("buyPayUpdate")
    public JSONObject buyPayUpdate(@RequestParam(value = "path", required = false) MultipartFile path, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        if (path == null || path.equals("")) {
            map.put("path", null);
        } else {
            SnowFlake snowFlake = new SnowFlake(1, 1);
            long pid = snowFlake.nextId();
            String floderName = String.valueOf(pid);
            String filepath = FileUtil.path(path, pathname, floderName);
            map.put("path", filepath);
        }
        map.put("sys_userid", JWTUtil.getUsername(request.getHeader("Authorization"), "pid"));
        json = buyPayInterface.buyPayUpdate(map);
        return json;
    }
}
