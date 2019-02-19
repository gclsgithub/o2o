package com.hytc.o2o.dao;

import com.hytc.o2o.entity.LocalAuth;
import com.hytc.o2o.entity.PersonInfo;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

    /**
     * 用户登陆
     *
     * @param localAuth
     * @return boolean
     */
    LocalAuth login(@Param("auth") LocalAuth localAuth);

    /**
     * 用户注册本地账号
     *
     * @param auth
     * @return
     */
    boolean insertIntoLocalAuth(@Param("auth") LocalAuth auth, @Param("userId") Long userId);

    /**
     * 注册用户信息<p>
     * 使用userGenerableKey 不能使用@Param（）注解
     *
     * @param personInfo
     * @return
     */
    Long insertIntoPersonInfo(PersonInfo personInfo);

    /**
     * 插入phoneAuth
     *
     * @param auth
     * @param userId
     * @return
     */
    boolean insertIntoPhoneAuth(@Param("aurh") LocalAuth auth, @Param("userId") Long userId);

    /**
     * 更新商品信息
     *
     * @param localAuth
     * @return
     */
    int updateAuth(@Param("auth") LocalAuth localAuth);

    /**
     * 查找userId
     *
     * @param auth
     * @return
     */
    Long findUserIdByUserNameAndPassword(@Param("auth") LocalAuth auth);

    /**
     *
     * @param localAuth
     */
    Integer updateLocalAuthInfo(@Param("auth")LocalAuth localAuth,@Param("oldPassWord")String oldPassword);
}
