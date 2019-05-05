package com.hytc.o2o.controller.product;

import com.hytc.o2o.service.ProductService;
import com.hytc.o2o.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/productmapper")
public class ProductMapperController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/jumpproduct/categoery" ,method = { RequestMethod.GET} )
    public String jumpProductCategoery(){
        return "/shop/productcategorymanage";
    }

    @RequestMapping(value = "/productaddmapper" ,method = {RequestMethod.GET})
    public String  jumpProductAdd(){return  "/shop/productedit";}

    @RequestMapping(value = "/editproductcategoery",method = {RequestMethod.GET})
    public String jumProductCategoery(){
        return "/shop/productCateoery";
    }

    @RequestMapping(value = "/delproduct" ,method = {RequestMethod.GET})
    public String  redictProductDel(HttpServletRequest request,
                                    @RequestParam("productId")String productId,
                                    @RequestParam("enableStatus")String enableStatus){


        String shopId = (String) request.getSession().getAttribute("shopId");

        //逻辑删除
        productService.delProduct(productId,enableStatus);

        return  "redirect:/shop/productmanage?shopId="+shopId;
    }
}
