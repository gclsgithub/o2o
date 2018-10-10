package com.hytc.o2o.dao;

import com.hytc.o2o.BaseTest;
import com.hytc.o2o.entity.Product;
import com.hytc.o2o.entity.ProductCategory;
import com.hytc.o2o.entity.Shop;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest extends BaseTest {

    @Autowired
    private ProductDao productDao;

    @Test
    public void TestAinsertPrdouctIntoDb(){

       /* List<Product> products = new ArrayList<>();
        Shop shop = new Shop();
        shop.setShopId(2L);
        ProductCategory productCategory = new ProductCategory();
        Product product = new Product();
        productCategory.setProductionCategoryId(2L);
        product.setCreateTime(LocalDateTime.now());
        product.setEnableStatus(1);
        product.setImgAddr("suoluetu");
        product.setProductName("name");
        product.setLastEditTime(LocalDateTime.now());
        product.setNormalPrice("100");
        product.setPriority(10);
        product.setProductDesc("aaa");
        product.setPromotionPrice("60");
        product.setShop(shop);
        product.setProductCategory(productCategory);
        products.add(product);
        Integer count = productDao.insertIntoProductImgDb(products);
        assertEquals(count,new Integer(1));*/

        Product product2 = new Product();
        ProductCategory productCategory = new ProductCategory();
        Shop shop = new Shop();
        shop.setShopId(2L);
        productCategory.setProductionCategoryId(2L);
        product2.setCreateTime(LocalDateTime.now());
        product2.setEnableStatus(1);
        product2.setImgAddr("suoluetu");
        product2.setLastEditTime(LocalDateTime.now());
        product2.setNormalPrice("100");
        product2.setPriority(10);
        product2.setProductDesc("aaa");
        product2.setProductName("name");
        product2.setPromotionPrice("60");
        product2.setShop(shop);
        product2.setProductCategory(productCategory);

        Integer id = productDao.insertPrdouctIntoDb(product2);

        System.out.println(id);
    }

}
