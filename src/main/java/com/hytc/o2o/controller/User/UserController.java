package com.hytc.o2o.controller.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/jumpto")
public class UserController {


    public static final String ONE = "1";

    public static final String TWO = "2";

    @GetMapping("/toLogin")
    public String jumpToLogin() {
        return "/shop/ownerlogin";
    }

    @GetMapping("/changepsw")
    public String jumpToChangePwd() {
        return "/shop/changepwd";
    }

    @GetMapping("/toregist")
    public String jumpToRegist(@RequestParam(value = "type") String type) {
        return "/shop/register";
    }

    @GetMapping("/logout")
    public String jumptologout(HttpServletRequest request) {
        request.getSession().removeAttribute("local");
        return "redirect:/jumpto/toLogin";
    }

}
