package com.hytc.o2o.controller.front;

import com.hytc.o2o.DTO.ShopExecution;
import com.hytc.o2o.entity.Area;
import com.hytc.o2o.entity.Shop;
import com.hytc.o2o.entity.ShopCategoery;
import com.hytc.o2o.service.AreaService;
import com.hytc.o2o.service.ProductCategoryService;
import com.hytc.o2o.service.ShopCategoeryService;
import com.hytc.o2o.service.ShopService;
import com.hytc.o2o.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shoplist")
public class ShopListController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private ShopCategoeryService shopCategoeryService;


    @GetMapping(value = "/listshoppageinfo")
    public Map<String, Object> listSearchInfo(HttpServletRequest request) {
        Map<String, Object> outputMap = new HashMap<>(6);
        //输入参数获取 一级类别(-1)/二级类别(自己本身)  此处不管前台传进来的是什么都作为类别
        Long shopCategoeryId = HttpRequestUtil.getLong(request, "shopCategoeryId");

        List<ShopCategoery> shopCategoeryList = null;
        List<Area> areaList = null;

        try {
            shopCategoeryList = shopCategoeryService.findShopCategoeryByParentId(shopCategoeryId);
            areaList = areaService.findAlls();
            outputMap.put("shopCategoeryList", shopCategoeryList);
            outputMap.put("areaList", areaList);
            outputMap.put("success", true);
            outputMap.put("areaList", areaList);
            outputMap.put("shopCategoeryList", shopCategoeryList);
        } catch (Exception e) {
            e.printStackTrace();
            outputMap.put("success", false);
            outputMap.put("message", "初期话失败");
        }
        return outputMap;
    }

    @RequestMapping(value = "/init", method = RequestMethod.POST)
    public Map<String, Object> initShopList(HttpServletRequest request) {

        Map<String, Object> outputMap = new HashMap<>(6);

        //输入参数获取 一级类别(-1)/二级类别(自己本身)  此处不管前台传进来的是什么都作为类别
        Long shopCategoeryId = HttpRequestUtil.getLong(request, "shopCategoeryId");


        int areaId = HttpRequestUtil.getInt(request, "areaId");

        int index = HttpRequestUtil.getInt(request, "index");

        int pageSize = HttpRequestUtil.getInt(request, "pageSize");

        String shopName = HttpRequestUtil.getString(request, "shopName");

        //设置参数
        Shop shop = SetSearchParams(shopCategoeryId, areaId, shopName);

        List<Shop> shopList = null;
        List<Area> areaList = null;
        List<ShopCategoery> shopCategoeryList = null;
        try {
            ShopExecution shopExectionOutput = shopService.getFrontShopList(shop, index, pageSize);

            shopList = shopExectionOutput.getShopList();
            areaList = areaService.findAlls();
            shopCategoeryList = shopCategoeryService.findShopCategoeryByParentId(shopCategoeryId);
            outputMap.put("success", true);
            outputMap.put("shopList", shopList);
            outputMap.put("areaList", areaList);
            outputMap.put("shopCategoeryList", shopCategoeryList);
        } catch (Exception e) {
            e.printStackTrace();
            outputMap.put("success", false);
            outputMap.put("message", "初期话失败");
        }
        return outputMap;
    }

    private Shop SetSearchParams(Long shopCategoeryId, int areaId, String shopName) {
        Shop shop = new Shop();
        ShopCategoery shopCategoery = new ShopCategoery();
        shopCategoery.setShopCategoeryId(shopCategoeryId);

        Area area = new Area();
        area.setAreaId(areaId);
        shop.setShopCategoery(shopCategoery);
        shop.setArea(area);
        shop.setShopName(shopName);
        return shop;
    }
}
