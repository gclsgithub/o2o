package com.hytc.o2o.dao;

import com.hytc.o2o.BaseTest;
import com.hytc.o2o.entity.PersonInfo;
import com.hytc.o2o.entity.Shop;
import com.hytc.o2o.entity.UserShopMap;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class UserShopMapDaoTest extends BaseTest {

    @Autowired
    private UserShopMapDao userShopMapDao;

    @Test
    public void findUserShopMapLists() {
        UserShopMap userShopMap = new UserShopMap();
        PersonInfo user = new PersonInfo();
        user.setUserId(2L);
        user.setName("大王");
        Shop shop = new Shop();
        shop.setShopId(2L);
        shop.setShopName("测试1");
        userShopMap.setUser(user);
        userShopMap.setCreateTime(new Date());
        userShopMap.setPoint(100);
        userShopMap.setUserShopId(1L);
        userShopMap.setShop(shop);
        List<UserShopMap> count = userShopMapDao.findUserShopMapLists(userShopMap,0,10);
        System.out.println(count.size());

    }

    @Test
    public void findUserShopMapCount() {
        UserShopMap userShopMap = new UserShopMap();
        PersonInfo user = new PersonInfo();
        user.setUserId(2L);
        user.setName("大王");
        Shop shop = new Shop();
        shop.setShopId(2L);
        shop.setShopName("测试1");
        userShopMap.setUser(user);
        userShopMap.setCreateTime(new Date());
        userShopMap.setPoint(100);
        userShopMap.setUserShopId(1L);
        userShopMap.setShop(shop);
        Integer count = userShopMapDao.findUserShopMapCount(userShopMap);
        Assert.assertEquals(count,new Integer(1));
    }

    @Test
    public void findUserShopMapByID() {

        UserShopMap find =  userShopMapDao.findUserShopMapByID(2L);
        Assert.assertNotNull(find);
    }

    @Test
    public void updateUserShopMap() {

        UserShopMap userShopMap = new UserShopMap();
        PersonInfo user = new PersonInfo();
        user.setUserId(2L);
        user.setName("大王");
        Shop shop = new Shop();
        shop.setShopId(2L);
        shop.setShopName("测试1");
        userShopMap.setUser(user);
        userShopMap.setCreateTime(new Date());
        userShopMap.setPoint(100);
        userShopMap.setUserShopId(1L);
        userShopMap.setShop(shop);
        userShopMapDao.updateUserShopMap(userShopMap);
    }

    @Test
    public void save() {
        UserShopMap userShopMap = new UserShopMap();
        PersonInfo user = new PersonInfo();
        user.setUserId(2L);
        user.setName("大王");
        Shop shop = new Shop();
        shop.setShopId(2L);
        shop.setShopName("测试1");
        userShopMap.setUser(user);
        userShopMap.setCreateTime(new Date());
        userShopMap.setPoint(100);
        userShopMap.setUserShopId(1L);
        userShopMap.setShop(shop);
        Integer shopId = userShopMapDao.save(userShopMap);
    }

    @Test
    public void delUserShopMap() {
        userShopMapDao.delUserShopMap(1L);
    }
}