package com.hytc.o2o.service;

import com.hytc.o2o.DTO.ImageHolder;
import com.hytc.o2o.entity.LocalAuth;

/**
 * 面向接口编程，他的好处就是编程模块化 程序中解耦
 */
public interface UserService {

    /**
     * 登陆操作
     * @param localAuth
     * @return LocalAuth
     */
    LocalAuth doLogin(LocalAuth localAuth);

    /**
     * 注册
     * @param auth
     * @return
     */
    boolean doRegist(LocalAuth auth, ImageHolder imageHolder);

    /**
     * 修改用户悉尼下
     * @param localAuth
     */
    void modify(LocalAuth localAuth);

    /**
     *
     * @param localAuth
     */
    int saveUserInfo(LocalAuth localAuth,String oldPassWord);
}
