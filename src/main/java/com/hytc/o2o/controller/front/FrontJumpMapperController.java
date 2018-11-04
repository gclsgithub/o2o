package com.hytc.o2o.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/frontend")
public class FrontJumpMapperController {

    /**
     * 主页
     *
     * @return
     */
    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String jumpToIndex() {
        return "/frontend/index";
    }

    /**
     * 消费记录
     */
    @RequestMapping(value = "/myrecord", method = RequestMethod.GET)
    public String jumpMySaleRecod() {
        return "/frontend/myrecord";
    }

    /**
     * 个人积分
     */
    @RequestMapping(value = "/mypoint", method = RequestMethod.GET)
    public String jumpPersonalCode() {
        return "/frontend/mypoint";
    }

    /**
     * 积分兑换记录
     */
    @RequestMapping(value = "/pointrecord", method = RequestMethod.GET)
    public String jumpPointRecord() {
        return "/frontend/pointrecord";
    }

    /**
     * 积分兑换记录
     */
    @RequestMapping(value = "/shoplist", method = RequestMethod.GET)
    public String jumpShopList() {
        return "/frontend/shoplist";
    }

    /**
     * shopList到ShopDetial
     */
    @RequestMapping(value = "/shopdetail", method = RequestMethod.GET)
    public String jumpShopDetial(){return "/frontend/shopdetail";}

    /**
     * ShopDetial到ProductDetial
     */
    @RequestMapping(value = "/productdetail", method = RequestMethod.GET)
    public String jumpProductDetial(){return "/frontend/productdetail";}
}
