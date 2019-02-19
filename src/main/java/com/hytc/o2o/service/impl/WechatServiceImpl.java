package com.hytc.o2o.service.impl;

import com.hytc.o2o.dao.WechatDao;
import com.hytc.o2o.entity.WechatAuth;
import com.hytc.o2o.service.WechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class WechatServiceImpl implements WechatService {

    @Autowired
    private WechatDao wechatDao;

    @Override
    public WechatAuth getWechatAuthByOpenId(String openId) {
        WechatAuth auth = wechatDao.findWechatAuthByOpenId(openId);

        if (ObjectUtils.isEmpty(auth)) {
            throw new RuntimeException("查询失败");
        }

        return auth;
    }

    @Override
    public WechatAuth getWechatAuthByUserId(String userId) {
        WechatAuth auth = wechatDao.findWechatAuthByuserId(userId);

        return auth;
    }

    @Override
    public void saveInfo(String userId, String openId) {
        wechatDao.saveInfo(userId,openId);
    }


}
