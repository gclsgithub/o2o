package com.hytc.o2o.controller.front;

import com.hytc.o2o.DTO.ShopExecution;
import com.hytc.o2o.entity.Area;
import com.hytc.o2o.entity.Shop;
import com.hytc.o2o.entity.ShopCategoery;
import com.hytc.o2o.service.AreaService;
import com.hytc.o2o.service.ProductCategoryService;
import com.hytc.o2o.service.ShopService;
import com.hytc.o2o.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ProductCategoryService productCategoryService;

    @RequestMapping(value = "/init" ,method = RequestMethod.POST)
    public Map<String,Object> initShopList(HttpServletRequest request){

        Map<String,Object> outputMap = new HashMap<>(6);

        //输入参数获取
        Long shopCategoeryId = HttpRequestUtil.getLong(request,"shopCategoeryId");

        int index =HttpRequestUtil.getInt(request,"index");

        int pageSize =HttpRequestUtil.getInt(request,"pageSize");

        Shop shop = new Shop();
        ShopCategoery shopCategoery = new ShopCategoery();
        shopCategoery.setShopCategoeryId(shopCategoeryId);
        shop.setShopCategoery(shopCategoery);


        List<Shop> shopList = null;
        List<Area> areaList = null;
        try {
            ShopExecution shopExectionOutput = shopService.getFrontShopList(shop, index, pageSize);

            shopList = shopExectionOutput.getShopList();
            areaList = areaService.findAlls();
            productCategoryService.searchProductCategoeryList(shopCategoeryId);
            outputMap.put("success", true);
            outputMap.put("shopList", shopList);
            outputMap.put("areaList", areaList);
        } catch (Exception e) {
            e.printStackTrace();
            outputMap.put("success", false);
            outputMap.put("message", "初期话失败");
        }


        return outputMap;
    }
}
