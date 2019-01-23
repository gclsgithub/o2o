package com.hytc.o2o.service;

import com.hytc.o2o.entity.WechatAuth;

public interface WechatService {

    WechatAuth getWechatAuthByOpenId(String openId);
}
