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
    void insertProductCategoryList(@Param("productCategoryList") List<ProductCategory> productCategoryList);
}
