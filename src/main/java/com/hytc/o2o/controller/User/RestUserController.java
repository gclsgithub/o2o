package com.hytc.o2o.controller.User;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hytc.o2o.DTO.ImageHolder;
import com.hytc.o2o.entity.LocalAuth;
import com.hytc.o2o.service.UserService;
import com.hytc.o2o.util.CodeUtil;
import com.hytc.o2o.util.HttpRequestUtil;
import com.hytc.o2o.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class RestUserController {

    @Autowired
    private UserService userService;

    /**
     * 登陆
     *
     * @param request
     * @return
     */
    @PostMapping("/login")
    public Map<String, Object> login(HttpServletRequest request) {
        Map<String, Object> output = new HashMap<>();

        String userName = HttpRequestUtil.getString(request, "userName");

        String passWord = HttpRequestUtil.getString(request, "passWord");

        LocalAuth localAuth = new LocalAuth();

        localAuth.setUserName(userName);

        //对密码进行MD5加密之后存放入数据库
        localAuth.setPassWord(MD5Util.getEncode(passWord));

        //判断是否登陆成功，登陆成功把Seesion存放在数据库中
        LocalAuth myauth = null;

        boolean isLogin = false;
        try {
            myauth = userService.doLogin(localAuth);

            if (ObjectUtils.isEmpty(myauth)) {
                isLogin = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            output.put("success", false);
            output.put("message", "登陆失败，用户名或者密码错误");
            return output;
        }

        //登陆成功的将USerName显示到
        if (isLogin) {

            //将用户信息存放入Session中
            request.getSession().setAttribute("local", myauth);
            output.put("success", true);
            output.put("name", userName);
            return output;
        } else {
            output.put("success", false);
            output.put("message", "登陆失败，用户名或者密码错误");
            return output;
        }
    }

    /**
     * 注册
     *
     * @param request
     * @return
     */
    @PostMapping(path = "/regix")
    public Map<String, Object> regist(HttpServletRequest request) {
        Map<String, Object> output = new HashMap<>();

        //验证码校验
        if (!CodeUtil.cheackVerfityCode(request)) {
            output.put("success", false);
            output.put("errorMsg", "输入了错误的验证码");
            return output;
        }

        String authStr = HttpRequestUtil.getString(request, "auth");

        String type = HttpRequestUtil.getString(request, "type");

        ObjectMapper objectMapper = new ObjectMapper();

        LocalAuth auth = null;
        try {
            auth = objectMapper.readValue(authStr, LocalAuth.class);
        } catch (IOException e) {
            e.printStackTrace();
            output.put("success", false);
            output.put("message", "系统异常");
        }

        //设置密码
        auth.setPassWord(MD5Util.getEncode(auth.getPassWord()));

        //设置用户类别
        auth.getPersonInfo().setUserType(Integer.valueOf(type));
        //处理文件流
        MultipartHttpServletRequest multipartHttpServletRequest = null;

        //处理缩略图
        ImageHolder thumbnail = null;

        //从RequestSession中获取文件流
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());

        try {
            if (multipartResolver.isMultipart(request)) {
                multipartHttpServletRequest = (MultipartHttpServletRequest) request;

                //取出缩略图，并且缩略图的ImageHolder的对象
                CommonsMultipartFile thumbnailFile = (CommonsMultipartFile) multipartHttpServletRequest.getFile("thumbnail");
                thumbnail = new ImageHolder(thumbnailFile.getOriginalFilename(), thumbnailFile.getInputStream());

            }
        } catch (Exception e) {
            throw new RuntimeException("图片出错");
        }

        if (ObjectUtils.isEmpty(auth)) {
            output.put("success", false);
            output.put("message", "系统异常");
            return output;
        }
        boolean isRegist = userService.doRegist(auth, thumbnail);
        if (isRegist) {
            output.put("success", true);
        }
        return output;
    }

    /**
     * 修改密码
     *
     * @param request
     * @return
     */
    @PostMapping("/modifypwd")
    public Map<String, Object> modify(HttpServletRequest request) {
        Map<String, Object> output = new HashMap<>();

        //验证码校验
        if (!CodeUtil.cheackVerfityCode(request)) {
            output.put("success", false);
            output.put("errorMsg", "输入了错误的验证码");
            return output;
        }

        String passWord = MD5Util.getEncode(HttpRequestUtil.getString(request, "passWord"));

        LocalAuth localAuth = (LocalAuth) request.getSession().getAttribute("local");

        localAuth.setPassWord(passWord);

        try {
            userService.modify(localAuth);
        } catch (RuntimeException e) {
            output.put("success", false);
            output.put("message", e.getMessage());
            return output;
        }
        output.put("success", true);
        return output;
    }


}
