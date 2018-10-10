package com.hytc.o2o.dao;

import com.hytc.o2o.BaseTest;
import com.hytc.o2o.entity.ProductImg;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductImgDaoTest extends BaseTest {

    @Autowired
    private ProductImgDao productImgDao;

    @Test
    public void TestABatchInsertImg(){
        List<ProductImg> productImgList = new ArrayList<>();
        ProductImg productImg = new ProductImg();
        productImg.setCreateTime(LocalDateTime.now());
        productImg.setImgAddr("wwwww");
        productImg.setImgDesc("双");
        productImg.setPriority(10);
        productImg.setProductId(1L);
        ProductImg productImg2 = new ProductImg();
        productImg2.setCreateTime(LocalDateTime.now());
        productImg2.setImgAddr("wwwww");
        productImg2.setImgDesc("双");
        productImg2.setPriority(10);
        productImg2.setProductId(1L);
        productImgList.add(productImg);
        productImgList.add(productImg2);
        Integer count  = productImgDao.batchInsertProductImg(productImgList);
        assertEquals(Integer.valueOf(2),count);
    }

/*    @Test
    public void TestB*/
}
