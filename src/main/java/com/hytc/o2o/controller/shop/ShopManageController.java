package com.hytc.o2o.controller.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hytc.o2o.DTO.ShopExecution;
import com.hytc.o2o.entity.PersonInfo;
import com.hytc.o2o.entity.Shop;
import com.hytc.o2o.enums.ShopStateEnum;
import com.hytc.o2o.service.ShopService;
import com.hytc.o2o.util.CodeUtil;
import com.hytc.o2o.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/shopadmin")
public class ShopManageController {

    @Autowired
    private ShopService shopService;


    @RequestMapping(value = "/registershop" , method = RequestMethod.POST)
    public Map<String ,Object> registerShop(HttpServletRequest request){
        //1.设置输出对象
        Map<String,Object> modelMap = new HashMap<>();

        //判断验证码是否正确
        if (!CodeUtil.cheackVerfityCode(request)){
            modelMap.put("success",false);
            modelMap.put("errorMsg","验证码信息错误");
            return modelMap;
        }

        //获取值
        String shopStr = HttpRequestUtil.getString(request,"shopStr");

        ObjectMapper objectMapper = new ObjectMapper();
        Shop shop = null;
        try {
            shop = objectMapper.readValue(shopStr,Shop.class);
        } catch (IOException e) {
            modelMap.put("success",false);
            modelMap.put("errorMsg",e.getMessage());
            return modelMap;
        }
        String userId = (String) request.getSession().getAttribute("userId");
        PersonInfo owner = new PersonInfo();
        //owner.setUserId(new Long(userId));
        shop.setOwner(owner);
        CommonsMultipartFile shopImg = null ;
        //从Servlet上下文中获取文件流
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        }
        //2.店铺注册
        if (shop != null && shopImg != null){
            try {
                ShopExecution shopExecution = shopService.addShop(shop,shopImg.getInputStream(),shopImg.getOriginalFilename());
                if (shopExecution.getStatus().equals(ShopStateEnum.CHECK.getStatus())){
                    modelMap.put("success",true);
                }else{
                    modelMap.put("success",false);
                    modelMap.put("errorMsg","存储失败");
                }
                return modelMap;
            } catch (IOException e) {
                modelMap.put("success",false);
                modelMap.put("errorMsg","文件存储路径错误");
                return modelMap;
            }
        }else {
            modelMap.put("success",false);
            modelMap.put("errorMsg","商品信息未正确填写");
            return modelMap;
        }


    }
}
