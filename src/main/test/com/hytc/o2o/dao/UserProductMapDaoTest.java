package com.hytc.o2o.dao;

import com.hytc.o2o.BaseTest;
import com.hytc.o2o.entity.PersonInfo;
import com.hytc.o2o.entity.Product;
import com.hytc.o2o.entity.Shop;
import com.hytc.o2o.entity.UserProductMap;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.*;

public class UserProductMapDaoTest extends BaseTest {

    @Autowired
    private UserProductMapDao userProductMapDao;

    @Test
    public void findUserProductMapList() {

        UserProductMap userProductMap = new UserProductMap();
        PersonInfo op = new  PersonInfo();
        op.setUserId(2L);

        PersonInfo user = new  PersonInfo();
        user.setUserId(2L);

        Product product = new Product();
        product.setProductId(19L);

        Shop shop = new Shop();
        shop.setShopId(3L);

        userProductMap.setCreateTime(new Date());
        userProductMap.setOperator(op);
        userProductMap.setPoint(100);
        userProductMap.setProduct(product);
        userProductMap.setUser(user);
        userProductMap.setShop(shop);

        userProductMapDao.findUserProductMapList(userProductMap,0,10);
    }

    @Test
    public void queryUserProductCount() {
        UserProductMap userProductMap = new UserProductMap();
        PersonInfo op = new  PersonInfo();
        op.setUserId(2L);

        PersonInfo user = new  PersonInfo();
        user.setUserId(2L);

        Product product = new Product();
        product.setProductId(19L);

        Shop shop = new Shop();
        shop.setShopId(3L);

        userProductMap.setCreateTime(new Date());
        userProductMap.setOperator(op);
        userProductMap.setPoint(100);
        userProductMap.setProduct(product);
        userProductMap.setUser(user);
        userProductMap.setShop(shop);

        userProductMapDao.queryUserProductCount(userProductMap);
    }

    @Test
    public void save() {
        UserProductMap userProductMap = new UserProductMap();
        PersonInfo op = new  PersonInfo();
        op.setUserId(2L);

        PersonInfo user = new  PersonInfo();
        user.setUserId(2L);

        Product product = new Product();
        product.setProductId(19L);

        Shop shop = new Shop();
        shop.setShopId(3L);

        userProductMap.setCreateTime(new Date());
        userProductMap.setOperator(op);
        userProductMap.setPoint(100);
        userProductMap.setProduct(product);
        userProductMap.setUser(user);
        userProductMap.setShop(shop);

        userProductMapDao.save(userProductMap);
    }
}