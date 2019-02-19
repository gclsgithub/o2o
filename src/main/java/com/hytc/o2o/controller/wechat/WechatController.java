package com.hytc.o2o.controller.wechat;

import com.hytc.o2o.entity.LocalAuth;
import com.hytc.o2o.entity.WechatAuth;
import com.hytc.o2o.service.WechatService;
import com.hytc.o2o.util.CodeUtil;
import com.hytc.o2o.util.MD5Util;
import com.hytc.o2o.util.WechatUtil;
import com.hytc.o2o.util.message.pojo.UserAccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class WechatController {

    @Autowired
    private WechatService wechatService;

    @GetMapping("/addShopAuthMap")
    public String addShopAuthMap(HttpServletRequest request, HttpServletResponse response) {

        //从request获取到用户信息
        WechatAuth wechatAuth = getEmoloyeeInfo(request);
        if (ObjectUtils.isEmpty(wechatAuth)) {

        }
        return null;
    }


    @PostMapping("/wechar/bindlocalauth")
    @ResponseBody
    public Map<String, Object> bindWechar(HttpServletRequest request) {

        Map<String, Object> modelMap = new HashMap<>();

        boolean statusChange = CodeUtil.cheackVerfityCode(request);

        //判断验证码是否正确
        if (!statusChange && !CodeUtil.cheackVerfityCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errorMsg", "验证码信息错误");
            return modelMap;
        }
        LocalAuth auth = (LocalAuth) request.getSession().getAttribute("local");

        if (ObjectUtils.isEmpty(auth)){
            modelMap.put("success", false);
            modelMap.put("errorMsg", "请登录");
        }

        Long userId = auth.getPersonInfo().getUserId();

        //查询是否在db绑定过
        WechatAuth wechatAuth = wechatService.getWechatAuthByUserId(String.valueOf(userId));

        if (!ObjectUtils.isEmpty(wechatAuth)) {
            modelMap.put("success", false);
            modelMap.put("errorMsg", "该账号已经绑定，无法重复绑定");
        }

        String openId = request.getParameter("openId");
        String userName = request.getParameter("userName");
        String password = MD5Util.getEncode(request.getParameter("password"));
        if (auth.getUserName().equals(userName) && auth.getPassWord().equals(password)){
            if (StringUtils.isEmpty(openId)){
                openId = UUID.randomUUID().toString();
            }
            try {
                wechatService.saveInfo(String.valueOf(userId), openId);
            } catch (Exception e){
                modelMap.put("success", false);
                modelMap.put("errorMsg", "系统异常");
            }
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errorMsg", "账户名或密码错误");
        }


        //绑定
        return modelMap;
    }

    @GetMapping("/wechar/bind")
    public String bindWechat() {
        return "/shop/ownerbind";
    }

    private WechatAuth getEmoloyeeInfo(HttpServletRequest request) {
        String code = request.getParameter("code");
        WechatAuth wechatAuth = null;
        if (!StringUtils.isEmpty(code)) {
            UserAccessToken token;
            try {
                token = WechatUtil.getUserAccessToken(code);
                String openId = token.getOpenId();
                request.getSession().setAttribute("openId", openId);
                wechatAuth = wechatService.getWechatAuthByOpenId(openId);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return wechatAuth;
    }
}
