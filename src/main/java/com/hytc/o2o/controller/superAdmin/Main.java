package com.hytc.o2o.controller.superAdmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/superadmin")
public class Main {

    /**
     * 跳转到superAdmin的main
     *
     * @return
     */
    @GetMapping("/main")
    public String jumpToMain() {
        return "/superAdmin/main";
    }

    /**
     * 跳转到superAdmin的login
     *
     * @return
     */
    @GetMapping("/login")
    public String jumpToLogin() {
        return "/superAdmin/login";
    }

    /**
     * 跳转到superAdmin的headLine
     *
     * @return
     */
    @GetMapping("/showheadlinemanage")
    public String jumpToHeadLine() {
        return "/superAdmin/headlinemanage";
    }

    /**
     * 跳转到superAdmin的AreaMange
     *
     * @return
     */
    @GetMapping("/areamanage")
    public String jumpToAreaMange() {
        return "/superAdmin/areamanage";
    }

    /**
     * 跳转到superAdmin的personinfomanage
     *
     * @return
     */
    @GetMapping("/personinfomanage")
    public String jumpToPersonInfo() {
        return "/superAdmin/personinfomanage";
    }

    /**
     * 跳转到superAdmin的shopcategorymanage
     *
     * @return
     */
    @GetMapping("/shopcategorymanage")
    public String jumpToShopCategoery() {
        return "/superAdmin/shopcategorymanage";
    }

    /**
     * 跳转到superAdmin的shopmanage
     *
     * @return
     */
    @GetMapping("/shopmanage")
    public String jumpToShopMange() {
        return "/superAdmin/shopmanage";
    }
}
