package com.hytc.o2o.service;

import com.hytc.o2o.DTO.ImageHolder;
import com.hytc.o2o.DTO.ShopExecution;
import com.hytc.o2o.entity.Product;
import com.hytc.o2o.entity.ProductCategory;
import com.hytc.o2o.entity.Shop;
import com.hytc.o2o.entity.ShopCategoery;

import java.io.InputStream;
import java.util.List;


/**
 * ShopService
 * @author hytc
 */
public interface ShopService {

    /**
     * 获取商品的DTO
     * @param ShopCondition
     * @param indexNum
     * @param pageSize
     * @return
     */
    ShopExecution getShopList(Shop ShopCondition,int indexNum,int pageSize);

    /**
     *添加商店
     * @param shop 商品信息
     * @param shopImgInputStream 商品图片
     */
    ShopExecution addShop(Shop shop, ImageHolder shopImgInputStream);

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

    /**
     * 查询Product列表
     * @param shopId
     * @return 商品List
     */
    List<Product> showShopProductionList(Integer shopId);

    /**
     * 获取所有以及商品分类的表
     * @return
     */
    List<ShopCategoery> getShopCategoeryList();

    ShopExecution getFrontShopList(Shop shop, int index, int pageSize);

    /**
     * 获取商品所有分类
     * @param shopId
     * @return
     */
    List<ProductCategory>  getProductCategoeryId(Long shopId);
}
