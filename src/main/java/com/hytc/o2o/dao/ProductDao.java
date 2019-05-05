package com.hytc.o2o.dao;

import com.hytc.o2o.entity.Product;
import com.hytc.o2o.entity.ProductCategory;
import com.hytc.o2o.entity.ProductImg;
import com.hytc.o2o.entity.ProductSellDaily;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ProductDao {

    /**
     * 获取商店商品的所有分类  按照权限排序
     *
     * @param shopId
     * @return
     */
    List<ProductCategory> getShopCategoryList(@Param("shopId") Long shopId);

    /**
     * 商品信息插入到数据库
     *
     * @param product
     * @return
     */
    Integer insertPrdouctIntoDb(@Param("product") Product product);

    /**
     * 批量插入商品信息到数据库
     *
     * @param products
     */
    Integer insertIntoProductImgDb(@Param("productlist") List<Product> products);

    /**
     * 查询关于Product的信息
     *
     * @param productId
     * @return
     */
    Product getProductInfo(@Param("productId") Long productId);

    /**
     * 获取商品分类
     *
     * @return
     */
    List<ProductCategory> getProductCategoryList(@Param("productId") Long productId);

    /**
     * 更新商品信息
     *
     * @param product
     * @return
     */
    int updateProduct(@Param("product") Product product);

    /**
     * 逻辑删除
     *
     * @param productId
     */
    int delProductByProductId(@Param("productId") String productId, @Param("status") String status);

    /**
     * 根据shopId查询出ProductCategoeryId
     *
     * @param shopId
     * @return
     */
    List<ProductCategory> getProductCategoryListByShopId(@Param("shopId") Long shopId);

    /**
     * 根据ShopId查询Produt相关信息
     * @param shopId
     * @return
     */
    List<Product> getProductListByShopId(@Param("shopId") Long shopId,@Param("indexNum")int index,@Param("pageSize")int pageSize);

    /**
     * 查询商品信息
     * @param product
     * @param index
     * @param pageSize
     * @return
     */
    List<Product> getProductList(@Param("product") Product product, @Param("index") int index, @Param("pageSize") int pageSize);

    /**
     * 存储销售信息
     *
     * @param productSellDaily
     * @return
     */
    int saveProductSellDailyInfo(@Param("productSellDaily") ProductSellDaily productSellDaily);

    /**
     * 获取主建Id
     * @param createTime
     * @return
     */
    Integer getProductSellId(@Param("createTime") String createTime);

    /**
     * 插入积分
     * @param userId
     * @param shopId
     * @param productId
     * @return
     */
    Integer savePoint(@Param("userId") Long userId,
                      @Param("shopId") Long shopId,
                      @Param("productId") Long productId,
                      @Param("point") Long point);
}
