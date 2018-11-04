package com.hytc.o2o.dao;

import com.hytc.o2o.entity.LocalAuth;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

public interface LocalAuthDao {


    /**
     * 根据用户名和密码查找用户
     *
     * @param userName
     * @param password
     * @return
     */
    LocalAuth getLocalByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);

    /**
     * 根据用户Id查找LocalAuth对象
     *
     * @param userId
     * @return
     */
    LocalAuth getLocalByUserId(@Param("userId") String userId);

    /**
     * 添加平台账号
     */
    int insertLocalAuth(@Param("localAuth")LocalAuth localAuth);

    /**
     * 修改用户信息
     * @param userId
     * @param userName
     * @param passWard
     * @return
     */
    int updateLocalAuth(@Param("userId")long userId,
                        @Param("userName")String userName,
                        @Param("passWord")String passWard,
                        @Param("newUserName")String newUserName,
                        @Param("newPassWord")String newPassWord,
                        @Param("time")LocalDateTime updateTime);


}
