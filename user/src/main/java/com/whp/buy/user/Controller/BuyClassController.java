package com.whp.buy.user.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buy.user.Interface.BuyClassInterface;
import com.whp.buy.user.Interface.SysClassInterface;
import com.whp.buy.utils.util.ImgUtilTg;
import com.whp.buy.utils.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/buyClass")
public class BuyClassController {
    @Autowired
    private BuyClassInterface buyClassInterface;

    @PostMapping("buyClassSelect")
    public JSONObject buyClassSelect(@RequestParam Map<String, Object> map) {
        JSONObject json = buyClassInterface.buyClassSelect(map);
        return json;
    }

    @PostMapping("buyClassSelectBySearch")
    public JSONObject buyClassSelectBySearch(@RequestParam Map<String, Object> map) {
        JSONObject json = buyClassInterface.buyClassSelectBySearch(map);
        return json;
    }

}
