package com.hytc.o2o.dao;

import com.hytc.o2o.entity.WechatAuth;
import org.apache.ibatis.annotations.Param;

public interface WechatDao {

    /**
     * 根据公开Id查找对象
     * @param openId
     * @return
     */
    WechatAuth findWechatAuthByOpenId(@Param("openId")String openId);
}
