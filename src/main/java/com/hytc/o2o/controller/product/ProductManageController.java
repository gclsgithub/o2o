package com.hytc.o2o.controller.product;

import com.hytc.o2o.entity.ProductCategory;
import com.hytc.o2o.service.ProductService;
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
@RequestMapping("/productmanage")
public class ProductManageController {

    @Autowired
    private ProductService productService;


    @RequestMapping(value = "/productinit",method = {RequestMethod.GET})
    public Map<String,Object> initProductCategory(HttpServletRequest request){
        Map<String,Object> output = new HashMap<>();

        String shopId = HttpRequestUtil.getString(request,"shopId");
        List<ProductCategory> productCategoryList = productService.getProductCategoeryList(shopId);

        output.put("success",true);
        output.put("productCategoeryList",productCategoryList);

        return output;
    }
}
