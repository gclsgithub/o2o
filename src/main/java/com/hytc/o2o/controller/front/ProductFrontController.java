package com.hytc.o2o.controller.front;

import com.hytc.o2o.entity.Product;
import com.hytc.o2o.entity.ProductCategory;
import com.hytc.o2o.entity.Shop;
import com.hytc.o2o.service.ProductService;
import com.hytc.o2o.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/productfront")
public class ProductFrontController {

    @Autowired
    private ProductService productService;

    @PostMapping(path = "searchproduct")
    public Map<String, Object> searchProduct(HttpServletRequest request) {
        Map<String, Object> output = new HashMap<>();

        Long productId = HttpRequestUtil.getLong(request, "productId");


        if (ObjectUtils.isEmpty(productId) || productId == -1) {
            output.put("success",false);
            output.put("message","商品不存在");
            return output;
        }

        Product product = new Product();

        product.setProductId(productId);

        List<Product> products = productService.getProductList(product, 1, 1);

        output.put("success",true);

        output.put("product",products.get(0));

        return output;
    }


    @PostMapping(path = "searchproductInfos")
    public Map<String, Object> SearchProductInfo(HttpServletRequest request) {

        Map<String, Object> outputMap = new HashMap<>();

        //参数获取
        int index = HttpRequestUtil.getInt(request, "index");

        int pageSize = HttpRequestUtil.getInt(request, "pageSize");

        Long shopId = HttpRequestUtil.getLong(request, "shopId") == -1 ?
                null : HttpRequestUtil.getLong(request, "shopId");

        Long productCategoeryId = HttpRequestUtil.getLong(request, "productCategoeryId") == -1
                ? null : HttpRequestUtil.getLong(request, "productCategoeryId");

        String productName = HttpRequestUtil.getString(request, "productName");


        //做成一个Dto作为传入到Service的参数
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(shopId);
        product.setShop(shop);

        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductionCategoryId(productCategoeryId);
        product.setProductCategory(productCategory);

        product.setProductName(productName);

        //调用ProductService
        try {
            List<Product> productList = productService.getProductList(product, index, pageSize);
            outputMap.put("success", true);
            outputMap.put("productList", productList);

        } catch (Exception e) {
            e.printStackTrace();
            outputMap.put("success", false);
            outputMap.put("message", "数据查询失败");

        }


        return outputMap;
    }

}
