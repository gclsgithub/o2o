package com.hytc.o2o.dao;


import com.hytc.o2o.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryDao {


    /**
     * 一扩插入
     * @param productCategoryList
     * @return
     */
    void insertProductCategoryList(List<ProductCategory> productCategoryList);

    /**
     * 物理删除类别
     * @param productId
     */
    void delProductCategoeryByProductId(@Param("productId") Long productId);



}
