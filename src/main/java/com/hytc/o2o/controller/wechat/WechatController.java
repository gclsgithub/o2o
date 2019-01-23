package com.hytc.o2o.controller.wechat;

import com.hytc.o2o.entity.WechatAuth;
import com.hytc.o2o.service.WechatService;
import com.hytc.o2o.util.WechatUtil;
import com.hytc.o2o.util.message.pojo.UserAccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class WechatController {

    @Autowired
    private WechatService wechatService;

    @GetMapping("/addShopAuthMap")
    public String addShopAuthMap(HttpServletRequest request, HttpServletResponse response){

        //从request获取到用户信息
        WechatAuth wechatAuth = getEmoloyeeInfo(request);
        if (ObjectUtils.isEmpty(wechatAuth)){

        }
        return null;
    }

    private WechatAuth getEmoloyeeInfo(HttpServletRequest request) {
        String code  = request.getParameter("code");
        WechatAuth wechatAuth = null;
        if (!StringUtils.isEmpty(code)){
            UserAccessToken token;
            try {
                token = WechatUtil.getUserAccessToken(code);
                String openId = token.getOpenId();
                request.getSession().setAttribute("openId",openId);
                wechatAuth = wechatService.getWechatAuthByOpenId(openId);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return  wechatAuth;
    }
}
