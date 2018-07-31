package com.hytc.o2o.service;

import com.hytc.o2o.DTO.ShopExecution;
import com.hytc.o2o.entity.Shop;

import java.io.InputStream;


/**
 * ShopService
 * @author hytc
 */
public interface ShopService {

    /**
     *添加商店
     * @param shop 商品信息
     * @param shopImgInputStream 商品图片
     */
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream,String fileName);

    /**
     * 根据shopId查询Shop实体
     * @param shopId
     * @return
     */
    ShopExecution updateShop(Long shopId,Shop shop);


    /**
     * 查询某一个Shop通过shopId
     * @param shopId
     * @return
     */
    Shop findSingleShopByShopId(Long shopId);
}
