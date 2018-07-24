package com.hytc.o2o.controller.shop;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = {"/html/shop"},method = RequestMethod.GET)
public class ShopMapperController {


    @RequestMapping(value = {"/shopoperation"},method = RequestMethod.GET)
    public String initShopOperationHtml(){
        //初始化也没中的
        return "/shop/shopoperation";
    }
}
