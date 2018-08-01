package com.hytc.o2o.dao;

import com.hytc.o2o.BaseTest;
import com.hytc.o2o.entity.Area;
import com.hytc.o2o.entity.PersonInfo;
import com.hytc.o2o.entity.Shop;
import com.hytc.o2o.entity.ShopCategoery;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopDaoTest extends BaseTest {

    @Autowired
    private ShopDao shopDao;

    @Test
    public void testqueryShopBySomeCondition(){
        Shop shop =new Shop();
        ShopCategoery shopCategoery = new ShopCategoery();
        Area area = new Area();
        PersonInfo owener = new PersonInfo();
        shop.setShopCategoery(shopCategoery);
        shop.setArea(area);
        shop.setOwner(owener);
        List<Shop> shopList = shopDao.queryShopBySomeCondition(shop,0,10);
    }

    @Test
    @Ignore
    public void testQueryShopCount(){
        Shop shop =new Shop();
        ShopCategoery shopCategoery = new ShopCategoery();
        Area area = new Area();
        PersonInfo owener = new PersonInfo();
        shop.setShopCategoery(shopCategoery);
        shop.setArea(area);
        shop.setOwner(owener);
        Integer count = shopDao.queryShopCount(shop);
        assertEquals(new Integer(1),count);
    }


    @Test
    @Ignore
    public void testInsertShop(){

        Shop myShop =new Shop();
        myShop.setAdvice("test");

        Area area=new Area();
        area.setAreaId(1);
        area.setAreaName("南苑");
        myShop.setArea(area);

        myShop.setCreateTime(new Date());
        myShop.setEnableStatus(1);
        myShop.setLastEditTime(new Date());

        PersonInfo owner =new PersonInfo();
        owner.setUserId(1L);
        owner.setName("test");
        myShop.setOwner(owner);
        myShop.setPhone("18360939450");
        myShop.setPriority(1);
        myShop.setShopAddr("北苑食堂一楼");

        ShopCategoery categoery = new ShopCategoery();
        categoery.setShopCategoeryId(1L);
        myShop.setShopCategoery(categoery);

        myShop.setShopDesc("新鲜可口");
        myShop.setShopImg("aa");
        myShop.setShopName("C0C0");

        Integer effectNum = shopDao.insertShop(myShop);

        assertEquals(new Integer(1),effectNum);

    }

    @Test
    public void testUpdateShop(){

        Shop myShop =new Shop();
        myShop.setShopId(3L);
        myShop.setAdvice("中文意见测试");

        Area area=new Area();
        area.setAreaId(1);
        myShop.setArea(area);

        myShop.setEnableStatus(1);
        myShop.setLastEditTime(new Date());

        myShop.setPhone("中文电话测试");
        myShop.setPriority(2);
        myShop.setShopAddr("中文地址测试");


        myShop.setShopDesc("新鲜可口");
        myShop.setShopImg("中文Img测试");
        myShop.setShopName("C01C01");

        Integer effectNum = shopDao.updateShop(myShop);

        assertEquals(new Integer(1),effectNum);


    }
}
