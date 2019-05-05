package com.hytc.o2o.controller.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hytc.o2o.DTO.ShopAuthMapExcution;
import com.hytc.o2o.entity.ShopAuthMap;
import com.hytc.o2o.enums.ShopAuthMapStateEnum;
import com.hytc.o2o.enums.ShopStateEnum;
import com.hytc.o2o.service.ShopAuthMapService;
import com.hytc.o2o.util.CodeUtil;
import com.hytc.o2o.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/shopauth")
public class ShopAuthManageMentController {

    @Autowired
    private ShopAuthMapService shopAuthMapService;

    @GetMapping("/initShopAuth")
    public Map<String, Object> initShopAuth(HttpServletRequest request,@RequestParam("shopId")String myshopId) {
        Map<String, Object> output = new HashMap<>();

        int index = HttpRequestUtil.getInt(request, "index");

        int pageSize = HttpRequestUtil.getInt(request, "pageSize");

        Long shopId = Long.valueOf(myshopId);

        if (ObjectUtils.isEmpty(shopId)) {
            output.put("success", false);
            output.put("message", "用户尚未登陆或者信息不存在，请重新登陆");
            return output;
        }

        ShopAuthMapExcution excution = shopAuthMapService.listShopAuthMapByShopId(shopId, index, pageSize);

        if (excution.getState().equals(ShopAuthMapStateEnum.SUCCESS.getState())) {
            output.put("lists", excution.getShopAuthMapList());
            output.put("count", excution.getShopAuthMapList().size());
            output.put("success", true);
            output.put("shopId", shopId);
        }

        return output;
    }

    @GetMapping("/shopauthedit")
    public Map<String, Object> findShopAuthById(@RequestParam(value = "shopAuthId") Long shopAuthId) {
        Map<String, Object> output = new HashMap<>();

        if (!ObjectUtils.isEmpty(shopAuthId) && shopAuthId > -1) {
            ShopAuthMapExcution shopAuthMapExcution = shopAuthMapService.getShopAuthMapByKeyId(shopAuthId);
            output.put("success", true);
            output.put("shopAuth", shopAuthMapExcution.getShopAuthMap());
        } else {
            output.put("success", false);
        }
        return output;
    }

    @PostMapping("/updateShopAuth")
    public Map<String, Object> updateShopAuth(HttpServletRequest request, String shopAuthStr) {


        Map<String, Object> output = new HashMap<>();

        //判断是二维码修改还是直接在网页上修改
        boolean statusChange = HttpRequestUtil.getBoolean(request, "statusChange");

        //判断验证码是否正确
        if (!statusChange && !CodeUtil.cheackVerfityCode(request)) {
            output.put("success", false);
            output.put("errorMsg", "验证码信息错误");
            return output;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        ShopAuthMap shopAuthMap = null;

        try {
            shopAuthMap = objectMapper.readValue(shopAuthStr, ShopAuthMap.class);
        } catch (IOException e) {
            output.put("success", false);
            output.put("errorMsg", "验证码信息错误");
            return output;
        }

        if (shopAuthMap.getShopAuthId() != -1) {
            //非空判断
            if (ObjectUtils.isEmpty(shopAuthMap)) {
                output.put("success", false);
                output.put("errorMsg", "输入的信息不正确");
                return output;
            }

            //检查做操的对方是否为店家本身，店家本身不支持修改
            if (!checkPermission(shopAuthMap.getShopAuthId())) {
                output.put("success", false);
                output.put("errorMsg", "无法修改店家自己");
                return output;
            }
        }

        ShopAuthMapExcution shopAuthMapExcution = shopAuthMapService.updateShopAuthMap(shopAuthMap);

        if (shopAuthMapExcution.getState().equals(ShopAuthMapStateEnum.SUCCESS.getState())) {
            output.put("success", true);
        } else {
            output.put("errorMsg", shopAuthMapExcution.getStateInfo());
        }
        return output;
    }

    //检查做操的对方是否为店家本身，店家本身不支持修改
    private boolean checkPermission(Long shopAuthId) {
        ShopAuthMapExcution grantedAuth = shopAuthMapService.getShopAuthMapByKeyId(shopAuthId);

        if (grantedAuth.getShopAuthMap().getTitleFlag() == 0) {
            return false;
        } else {
            return true;
        }
    }
}
