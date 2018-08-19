package com.hytc.o2o.controller.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/productmapper")
public class ProductMapperController {

    @RequestMapping(value = "/jumpproduct/categoery" ,method = { RequestMethod.GET} )
    public String jumpProductCategoery(){
        return "/shop/productcategorymanage";
    }

    @RequestMapping(value = "/productaddmapper" ,method = {RequestMethod.GET})
    public String  jumpProductAdd(){return  "/shop/productedit";}
}
