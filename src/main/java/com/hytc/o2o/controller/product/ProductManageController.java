package com.hytc.o2o.controller.product;

import com.hytc.o2o.DTO.ProductCategoryExcution;
import com.hytc.o2o.DTO.ResultSource;
import com.hytc.o2o.entity.ProductCategory;
import com.hytc.o2o.enums.ProductCategoryEnum;
import com.hytc.o2o.service.ProductCategoryService;
import com.hytc.o2o.service.ProductService;
import com.hytc.o2o.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping(value = "initproductedit" ,method = {RequestMethod.GET})
    public ResultSource initProductEdit(HttpServletRequest request){
        ResultSource output = null;
        Long productId = HttpRequestUtil.getLong(request,"productId");
        ProductCategoryExcution productCategoryExcution = productService.getProduct(productId);

        return output;
    }

    /**
     * 非逻辑删除，物理删除类别
     * @param request
     * @return
     */
    @RequestMapping(path = "delproductcategoery" , method = {RequestMethod.POST})
    public ResultSource delProductCategoery(HttpServletRequest request, @RequestBody ProductCategory productCategory){
        ResultSource output = null;
        Long productId = productCategory.getProductionCategoryId();
        ProductCategoryExcution productCategoryExcution = productCategoryService.delProductCategoery(productId);
        if(productCategoryExcution.getProductCategoryEnum()==ProductCategoryEnum.SUCCESS){
            output= new ResultSource(true,null);
        }else{
            output= new ResultSource(false,null);
        }
        return output;
    }

    /**
     *
     * @param request HttpServletRequest
     * @param productCategoryList 入力参数是以JSON数据格式进入，必须使用@RequestBody这个注解
     * @return
     */
    @RequestMapping(value = "/insertlist" ,method = {RequestMethod.POST})
    public ResultSource insertList2Database(HttpServletRequest request, @RequestBody List<ProductCategory> productCategoryList){
        ResultSource output = null;
        String shopId = HttpRequestUtil.getString(request,"shopId");

        for (ProductCategory pc:productCategoryList){
            pc.setShopId(Long.valueOf(shopId));
        }
        if (null != productCategoryList &&  productCategoryList.size() > 0) {
            ProductCategoryExcution productCategoryExcution = productCategoryService.batchAndProductCategoery(productCategoryList);
            if(productCategoryExcution.getProductCategoryEnum()==ProductCategoryEnum.SUCCESS){
                output= new ResultSource(true,null);
            }
        }else{
            output= new ResultSource(false,null);
        }
        return output;
    }

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
