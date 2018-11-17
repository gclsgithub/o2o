package com.hytc.o2o.controller.front;

import com.hytc.o2o.entity.HeadLine;
import com.hytc.o2o.entity.ProductCategory;
import com.hytc.o2o.entity.ShopCategoery;
import com.hytc.o2o.service.HeadLineService;
import com.hytc.o2o.service.ProductCategoryService;
import com.hytc.o2o.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 前台的界面全是Rest请求
 */
@RestController
@RequestMapping("frontend")
public class HeadLineController {

    @Autowired
    private HeadLineService headLineService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ShopService shopService;

    @Value("${ImageBasePath}")
    private String basePath;

    @Value("${filePath}")
    private String filePath;

    @RequestMapping(value = "showIndex" ,method = RequestMethod.POST)
    public Map<String, Object> showMainMenu() {
        Map<String, Object> mapMap = new HashMap<>();

        // 取出头条
        try {
            List<HeadLine> headLineList = headLineService.getUpHeadLine();
            mapMap.put("headLineList", headLineList);
        } catch (Exception ex) {
            mapMap.put("success", false);
            mapMap.put("message", "头条取得失败");
        }

        //取出一级分类信息
        try {
            List<ShopCategoery> shopCategoerieList = shopService.getShopCategoeryList();

            mapMap.put("shopCategoerieList", shopCategoerieList);
        } catch (Exception e) {
            e.printStackTrace();
            mapMap.put("success", false);
            mapMap.put("message", "商品列表");
        }
        return mapMap;
    }
}
