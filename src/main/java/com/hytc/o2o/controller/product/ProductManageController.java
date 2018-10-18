package com.hytc.o2o.controller.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hytc.o2o.DTO.*;
import com.hytc.o2o.entity.Product;
import com.hytc.o2o.entity.ProductCategory;
import com.hytc.o2o.entity.Shop;
import com.hytc.o2o.enums.ProductCategoryEnum;
import com.hytc.o2o.enums.ProductStateEnum;
import com.hytc.o2o.exceptions.ProductRuntimeException;
import com.hytc.o2o.service.ProductCategoryService;
import com.hytc.o2o.service.ProductService;
import com.hytc.o2o.util.CodeUtil;
import com.hytc.o2o.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/productmanage")
public class ProductManageController {

    @Autowired
    private ProductService productService;

    @Value("${imageFileMaxFileUploadCount}")
    private int IMAGEMAXCOUNT;

    @Autowired
    private ProductCategoryService productCategoryService;


    /**
     * 搜索商品分类信息
     * @param request
     * @return
     */
    @PostMapping("showPeoductCategoey")
    public Map<String,Object> showPeoductCategoey(HttpServletRequest request){
        Map<String,Object> dataMap = new HashMap<>();

        String productCategoryId = HttpRequestUtil.getString(request,"productCategoryId");

        ProductCategory outPut = productCategoryService.searchProductCategoeryByProductCategoeryId(Long.valueOf(productCategoryId));
        dataMap.put("product",outPut);
        dataMap.put("success",true);

        return dataMap;
    }

    /**
     * 初期化方法 查询商品信息
     * @param request
     * @param productId 商品信息
     * @return
     */
    @RequestMapping(value = "initproductedit" ,method = {RequestMethod.GET})
    public Map<String,Object> initProductEdit(HttpServletRequest request, @RequestParam("productId")String productId){

        Map<String,Object> outDate = new HashMap<>();

        outDate.put("productId",productId);
        String shopId = (String) request.getSession().getAttribute("shopId");
        ProductAndCategoeryDto serviceOutput = null;
        if (!StringUtils.isEmpty(shopId)) {

            Long productIdL= null;
            if (!StringUtils.isEmpty(productId)){
                productIdL = Long.valueOf(productId);
            }
            serviceOutput = productService.getProduct(productIdL);
            outDate.put("Product",serviceOutput.getProduct());
            outDate.put("ProductList",serviceOutput.getProductCategoryList());
            outDate.put("success",true);
        }
        outDate.put("shopId",shopId);
        outDate.put("imageMaxLength",IMAGEMAXCOUNT);
        return  outDate;
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

    /**
     * 获取
     * @param request
     * @return
     */
    @RequestMapping(value = "/productinit",method = {RequestMethod.GET})
    public Map<String,Object> initProductCategory(HttpServletRequest request){
        Map<String,Object> output = new HashMap<>();

        String shopId = HttpRequestUtil.getString(request,"shopId");
        List<ProductCategory> productCategoryList = new ArrayList<>();
        if (!StringUtils.isEmpty(shopId)) {
             productCategoryList = productService.getProductCategoeryList();
        }
        output.put("success",true);
        output.put("productCategoeryList",productCategoryList);

        return output;
    }

    /**
     * 新增加商品信息 (修正商品的信息)
     * @param request
     * @return
     */
    @RequestMapping(value = "/addproduct" ,method = {RequestMethod.POST})
    public Map<String,Object> addProduct(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();

        String productId = HttpRequestUtil.getString(request,"productId");

        modelMap.put("productId",productId);
        //验证码校验
        if (!CodeUtil.cheackVerfityCode(request)){
            modelMap.put("success",false);
            modelMap.put("errorMsg","输入了错误的验证码");
            return modelMap;
        }

        //接受前段参数的变量的初始化数据
        //包括商品信息，缩略图，详情图列表实体
        ObjectMapper objectMapper = new ObjectMapper();

        Product product = null;

        String productStr = HttpRequestUtil.getString(request,"productStr");

        //处理文件流
        MultipartHttpServletRequest multipartHttpServletRequest = null;

        //处理缩略图
        ImageHolder thumbnail = null;

        //处理详情图
        List<ImageHolder> productImgList = new ArrayList<>();

        //从RequestSession中获取文件流
        CommonsMultipartResolver multipartResolver =new CommonsMultipartResolver(
                request.getSession().getServletContext());

        try{
            if (multipartResolver.isMultipart(request)){
                multipartHttpServletRequest = (MultipartHttpServletRequest) request;

                //取出缩略图，并且缩略图的ImageHolder的对象
                CommonsMultipartFile thumbnailFile = (CommonsMultipartFile) multipartHttpServletRequest.getFile("thumbnail");
                thumbnail = new ImageHolder(thumbnailFile.getOriginalFilename(),thumbnailFile.getInputStream());

                //构建详情图，List<ImageHolder>
                for (int i = 0 ;i < IMAGEMAXCOUNT ; i++ ){
                    CommonsMultipartFile productImgFile = (CommonsMultipartFile) multipartHttpServletRequest.getFile("productImg"+i);

                    if (!ObjectUtils.isEmpty(productImgFile)){
                        ImageHolder productImg = new ImageHolder(productImgFile.getOriginalFilename(),productImgFile.getInputStream());
                        productImgList.add(productImg);
                    } else {

                        //若取出的第i哥详情图文件流为空，则中止循环
                        break;
                    }
                }
            }else {
                modelMap.put("success",false);
                modelMap.put("errMsg","上传图片不能为空");
                return modelMap;
            }
        }catch (Exception ex){
            modelMap.put("success",false);
            modelMap.put("errMsg",ex.getMessage());
            return modelMap;
        }

        //处理上传的文件到数据库，以及存储项目之中
        try{
            //读取上传的Json数据,将前台传过来的表单String流并将其转化成Product实体类
            product = objectMapper.readValue(productStr,Product.class);
        }catch (Exception ex){
            modelMap.put("success",false);
            modelMap.put("errMsg",ex.getMessage());
            return modelMap;
        }

        //添加商品信息,当商品信息，商品缩略图，商品详细图都存在的时候才可以保存
        if (!ObjectUtils.isEmpty(product) && !ObjectUtils.isEmpty(thumbnail) && !CollectionUtils.isEmpty(productImgList)){

            try {
                String shopId = (String) request.getSession().getAttribute("shopId");
                Shop shop = new Shop();
                shop.setShopId(new Long(shopId));
                product.setShop(shop);

                ProductExcution pe = productService.addProduct(product,thumbnail,productImgList);

                if (pe.getStatus() == ProductStateEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                }else {
                    modelMap.put("success",false);
                    modelMap.put("errMsg",pe.getStateInfo());
                }
            }catch (ProductRuntimeException ex){
                modelMap.put("success",false);
                modelMap.put("errMsg",ex.getMessage());
                return modelMap;
            }
        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入商品信息");
        }
        return modelMap;
    }


}
