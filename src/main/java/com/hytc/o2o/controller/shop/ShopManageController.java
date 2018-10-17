package com.hytc.o2o.controller.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hytc.o2o.DTO.ImageHolder;
import com.hytc.o2o.DTO.ShopExecution;
import com.hytc.o2o.entity.*;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shopadmin")
public class ShopManageController {

    @Autowired
    private ShopService shopService;


    @RequestMapping(value = "/productmanageinit",method = RequestMethod.GET)
    public Map<String,Object> getShopProduction(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        Integer shopId = HttpRequestUtil.getInt(request,"shopId");
        if (shopId == -1 ){
            String sessionShopId = (String) request.getSession().getAttribute("shopId");
            shopId = Integer.valueOf(sessionShopId);
        }
        List<Product> productList = shopService.showShopProductionList(shopId);
        modelMap.put("productList",productList);
        modelMap.put("success",true);
        return modelMap;
    }

    @RequestMapping(value = "/getshopmanageinfo", method = RequestMethod.GET)
    public Map<String, Object> getShopManageInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        //获取ShopId判定是否登陆从而控制用户查看某个店铺信息
        Long shopId = HttpRequestUtil.getLong(request, "shopId");
        if (shopId <= 0) {
            Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
            if (currentShop == null) {
                modelMap.put("redirect", true);
                modelMap.put("url", "/shopadmin/shoplist");
            } else {
                modelMap.put("redirect", false);
                modelMap.put("shopId", currentShop.getShopId());
            }
        } else {
            Shop shop = new Shop();
            shop.setShopId(shopId);
            request.getSession().setAttribute("currentShop", shop);
        }
        return modelMap;
    }


    @RequestMapping(value = "/shoplist", method = RequestMethod.GET)
    public Map<String, Object> findShopListByOwnerId(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        //从Session中获取UserId
        PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");

        Area area = new Area();
        ShopCategoery shopCategoery = new ShopCategoery();
        Shop shopCondition = new Shop();
        shopCondition.setOwner(user);
        shopCondition.setShopCategoery(shopCategoery);
        shopCondition.setArea(area);

        try {
            ShopExecution shopExecution = shopService.getShopList(shopCondition, 0, 100);
            modelMap.put("shopList", shopExecution.getShopList());
            modelMap.put("user", user);
            modelMap.put("success", true);
        } catch (Exception ex) {
            modelMap.put("success", false);
            modelMap.put("errMsg", ex.getMessage());
        }
        return modelMap;
    }


    @RequestMapping(value = "/queryShop", method = RequestMethod.GET)
    public Map<String, Object> findSingleShop(HttpServletRequest request) {

        Map<String, Object> modelMap = new HashMap<>();

        Long shopId = HttpRequestUtil.getLong(request, "shopId");
        if (shopId != null) {
            Shop shop = shopService.findSingleShopByShopId(shopId);
            modelMap.put("success", true);
            modelMap.put("shop", shop);
        } else {
            modelMap.put("success", false);
        }
        return modelMap;
    }


    @RequestMapping(value = "/updateShop", method = RequestMethod.PUT)
    public Map<String, Object> updateShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();

        //判断验证码是否正确
        if (!CodeUtil.cheackVerfityCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errorMsg", "验证码信息错误");
            return modelMap;
        }

        Long shopId = HttpRequestUtil.getLong(request, "shopId");
        //获取值
        String shopStr = HttpRequestUtil.getString(request, "shopStr");

        ObjectMapper objectMapper = new ObjectMapper();
        Shop shop = null;
        try {
            shop = objectMapper.readValue(shopStr, Shop.class);
        } catch (IOException e) {
            modelMap.put("success", false);
            modelMap.put("errorMsg", e.getMessage());
            return modelMap;
        }
        String userId = (String) request.getSession().getAttribute("userId");
        PersonInfo owner = new PersonInfo();
        //owner.setUserId(new Long(userId));
        shop.setOwner(owner);
        CommonsMultipartFile shopImg = null;
        //从Servlet上下文中获取文件流
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        }
        if (shop != null && userId != null) {
            try {
                ShopExecution shopExecution = shopService.updateShop(shopId, shop);
                modelMap.put("success", false);
                modelMap.put("shop", shopExecution.getShop());
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errorMsg", e.getMessage());
            }
        }
        return modelMap;
    }


    @RequestMapping(value = "/registershop", method = RequestMethod.POST)
    public Map<String, Object> registerShop(HttpServletRequest request) {
        //1.设置输出对象
        Map<String, Object> modelMap = new HashMap<>();

        //判断验证码是否正确
        if (!CodeUtil.cheackVerfityCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errorMsg", "验证码信息错误");
            return modelMap;
        }

        //获取值
        String shopStr = HttpRequestUtil.getString(request, "shopStr");

        ObjectMapper objectMapper = new ObjectMapper();
        Shop shop = null;
        try {
            shop = objectMapper.readValue(shopStr, Shop.class);
        } catch (IOException e) {
            modelMap.put("success", false);
            modelMap.put("errorMsg", e.getMessage());
            return modelMap;
        }
        String userId = (String) request.getSession().getAttribute("userId");
        PersonInfo owner = new PersonInfo();
        //owner.setUserId(new Long(userId));
        shop.setOwner(owner);
        CommonsMultipartFile shopImg = null;
        //从Servlet上下文中获取文件流
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        }
        //2.店铺注册
        if (shop != null && shopImg != null) {
            try {
                ImageHolder holder = new ImageHolder(shopImg.getOriginalFilename(),shopImg.getInputStream());
                ShopExecution shopExecution = shopService.addShop(shop,holder);
                if (shopExecution.getStatus().equals(ShopStateEnum.CHECK.getStatus())) {
                    modelMap.put("success", true);
                    // 若shop创建成功，则加入session中，作为权限使用
                    List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
                    if (shopList != null && shopList.size() > 0) {
                        shopList.add(shopExecution.getShop());

                    } else {
                        shopList = new ArrayList<>();
                        shopList.add(shopExecution.getShop());
                    }
                    request.getSession().setAttribute("shopList", shopList);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errorMsg", "存储失败");
                }
                return modelMap;
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errorMsg", "文件存储路径错误");
                return modelMap;
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errorMsg", "商品信息未正确填写");
        }
        return modelMap;
    }



    @RequestMapping(value = "/showShopList" , method = RequestMethod.GET)
    public Map<String ,Object> showShopList(HttpServletRequest request) {
        Map<String,Object> modelMap = new HashMap<>();

        //获取值
        String shopStr = HttpRequestUtil.getString(request, "shopStr");

        ObjectMapper objectMapper = new ObjectMapper();
        Shop shop = null;
        try {
            if (!"".equals(shopStr) && shopStr != null) {
                shop = objectMapper.readValue(shopStr, Shop.class);
            }else{
                Area area =new Area();

                PersonInfo owner = new PersonInfo();

                ShopCategoery shopCategoery = new ShopCategoery();
                shop = new Shop();
                shop.setArea(area);
                shop.setOwner(owner);
                shop.setShopCategoery(shopCategoery);
            }
        } catch (IOException e) {
            modelMap.put("success", false);
            modelMap.put("errorMsg", e.getMessage());
            return modelMap;
        }

        ShopExecution shopExecution = shopService.getShopList(shop,0,20);

        modelMap.put("success",true);
        modelMap.put("shopList",shopExecution.getShopList());

        return modelMap;
    }
}
