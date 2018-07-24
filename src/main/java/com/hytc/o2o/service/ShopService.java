package com.hytc.o2o.service;

import com.hytc.o2o.DTO.ShopExecution;
import com.hytc.o2o.entity.Shop;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;


/**
 * ShopService
 * @author hytc
 */
public interface ShopService {

    /**
     *添加商店
     * @param shop 商品信息
     * @param shopImg 商品图片
     */
    ShopExecution addShop(Shop shop, File shopImg);
}
