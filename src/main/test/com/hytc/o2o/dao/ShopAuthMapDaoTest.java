package com.hytc.o2o.dao;

import com.hytc.o2o.BaseTest;
import com.hytc.o2o.entity.PersonInfo;
import com.hytc.o2o.entity.Shop;
import com.hytc.o2o.entity.ShopAuthMap;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ShopAuthMapDaoTest extends BaseTest {

    @Autowired
    private ShopAuthMapDao shopAuthMapDao;

    @Test
    public void findeShopAuthMapListByshopId() {
        List<ShopAuthMap> lsit =  shopAuthMapDao.findeShopAuthMapListByshopId(2L,0,10);
        System.out.println(lsit);
    }

    @Test
    public void findShopAuthContByShopId() {
        int count = shopAuthMapDao.findShopAuthContByShopId(2L);
        System.out.println(count);
    }

    @Test
    public void save() {
        ShopAuthMap shopAuthMap = new ShopAuthMap();
        Shop shop = new Shop();
        shop.setShopId(2L);
        shopAuthMap.setShop(shop);

        PersonInfo employee = new PersonInfo();
        employee.setUserId(2L);

        shopAuthMap.setEmmployee(employee);

        shopAuthMap.setCreateTime(new Date());

        shopAuthMap.setEmableStatus(1);

        shopAuthMap.setJobName("AAA");

        shopAuthMap.setTitleFlag(1);

        shopAuthMap.setShopAuthId(1L);

        shopAuthMapDao.save(shopAuthMap);
    }

    @Test
    public void updateShopAuth() {
        ShopAuthMap shopAuthMap = new ShopAuthMap();
        Shop shop = new Shop();
        shop.setShopId(2L);
        shopAuthMap.setShop(shop);

        PersonInfo employee = new PersonInfo();
        employee.setUserId(2L);

        shopAuthMap.setEmmployee(employee);

        shopAuthMap.setCreateTime(new Date());

        shopAuthMap.setEmableStatus(1);

        shopAuthMap.setJobName("AAA");

        shopAuthMap.setTitleFlag(1);

        shopAuthMap.setShopAuthId(1L);
        shopAuthMapDao.updateShopAuth(shopAuthMap);
    }

    @Test
    public void findShopAufindShopAurhMapByIdrhMapById() {
        ShopAuthMap shopAuthMap = shopAuthMapDao.findShopAurhMapById(1L);
        System.out.println(shopAuthMap);
    }
}