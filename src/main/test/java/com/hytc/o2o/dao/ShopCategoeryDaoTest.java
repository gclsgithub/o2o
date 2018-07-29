package com.hytc.o2o.dao;

import com.hytc.o2o.BaseTest;
import com.hytc.o2o.entity.ShopCategoery;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopCategoeryDaoTest extends BaseTest {
    @Autowired
    private ShopCategoeryDao shopCategoeryDao;

    @Test
    public void FindAllsTest(){
        ShopCategoery shopCategoery1 = new ShopCategoery();

        shopCategoery1.setParentId(null);

        List<ShopCategoery> shopCategoeryList = shopCategoeryDao.findAlls(shopCategoery1);
        for (ShopCategoery shopCategoery:shopCategoeryList) {
            assertEquals(new Long(1),shopCategoery.getShopCategoeryId());
        }
    }
}
