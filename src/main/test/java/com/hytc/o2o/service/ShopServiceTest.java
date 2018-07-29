package com.hytc.o2o.service;

import com.hytc.o2o.BaseTest;
import com.hytc.o2o.DTO.ShopExecution;
import com.hytc.o2o.entity.Area;
import com.hytc.o2o.entity.PersonInfo;
import com.hytc.o2o.entity.Shop;
import com.hytc.o2o.entity.ShopCategoery;
import com.hytc.o2o.enums.ShopStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.File;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ShopServiceTest extends BaseTest {

    @Autowired
    private ShopService shopService;

    @Test
    public void addShopTest(){

        Shop myShop =new Shop();
        myShop.setAdvice("test");

        Area area=new Area();
        area.setAreaId(1);
        myShop.setArea(area);

        myShop.setCreateTime(new Date());
        myShop.setEnableStatus(ShopStateEnum.CHECK.getStatus());
        myShop.setLastEditTime(new Date());

        PersonInfo owner =new PersonInfo();
        owner.setUserId(1L);

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


        File imageFile = new File("/Users/gcl/Documents/MyPic.jpg");

       // ShopExecution shopExecution = shopService.addShop(myShop,imageFile);

       // assertEquals("0",shopExecution.getStatus());

    }


}
