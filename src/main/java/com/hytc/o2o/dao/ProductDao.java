package com.hytc.o2o.dao;

import com.hytc.o2o.entity.Product;
import com.hytc.o2o.entity.ProductCategory;
import com.hytc.o2o.entity.ProductImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {

    /**
     * 获取产品分类List
     * @param shopId
     * @return
     */
    List<ProductCategory> getShopProductCategoryList(@Param("shopId") Long shopId);

    /**
     * 商品信息插入到数据库
     * @param product
     * @return
     */
    Integer insertPrdouctIntoDb(@Param("product") Product product);

    /**
     * 批量插入商品信息到数据库
     * @param products
     */
    Integer insertIntoProductImgDb(@Param("productlist")List<Product> products);

    /**
     * 查询关于Product的信息
     * @param productId
     * @return
     */
    Product getProductInfo(@Param("productId")Long productId);
}
