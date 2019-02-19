package com.hytc.o2o.service.impl;

import com.hytc.o2o.DTO.ImageHolder;
import com.hytc.o2o.dao.UserDao;
import com.hytc.o2o.entity.LocalAuth;
import com.hytc.o2o.service.UserService;
import com.hytc.o2o.util.ImageUtil;
import com.hytc.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Random;

@Service
public class UserServcieImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public LocalAuth doLogin(LocalAuth localAuth) {

        LocalAuth certificationUser = userDao.login(localAuth);

        if (ObjectUtils.isEmpty(certificationUser)) {
            throw new RuntimeException("login fail");
        }
        return certificationUser;
    }

    /**
     * 注册
     *
     * @param auth
     * @return
     */
    @Override
    public boolean doRegist(LocalAuth auth, ImageHolder imageHolder) {


        Random random = new Random();
        String addr = addThumbnail(new Long(random.nextInt(10)), imageHolder);

        auth.getPersonInfo().setProfileImg(addr);

        Long userId = 0L;
        try {
            userDao.insertIntoPersonInfo(auth.getPersonInfo());

            //取出userId
            userId = userDao.findUserIdByUserNameAndPassword(auth);
        } catch (Exception e) {
            e.printStackTrace();
        }

        auth.getPersonInfo().setProfileImg(addr);
        if (userId != 0) {
            boolean flag = userDao.insertIntoLocalAuth(auth, userId);
            if (flag) {
                boolean insertFlag = userDao.insertIntoPhoneAuth(auth, userId);
                return insertFlag;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public void modify(LocalAuth localAuth) {
        int count = 0;
        try {
            count = userDao.updateAuth(localAuth);
        } catch (Exception e) {

            //不存在的异常
            e.printStackTrace();
        }
        if (count == 0) {
            throw new RuntimeException("更新的数据不存");
        }
    }

    @Override
    public void saveUserInfo(LocalAuth localAuth,String oldPassWord) {
        userDao.updateLocalAuthInfo(localAuth,oldPassWord);
    }

    /**
     * 处理缩略图文件流
     *
     * @param userId
     * @param thumnail
     */
    private String addThumbnail(Long userId, ImageHolder thumnail) {
        String dest = PathUtil.getShopImagePath(userId);

        String fileName = thumnail.getImageName();
        String thumnnailAddr = ImageUtil.generateThumbnail(thumnail.getImage(), dest, fileName);

        return thumnnailAddr;
    }
}
