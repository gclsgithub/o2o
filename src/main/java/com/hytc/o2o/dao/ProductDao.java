package com.hytc.o2o.dao;

import com.hytc.o2o.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {

    /**
     * 获取产品分类List
     * @param shopId
     * @return
     */
    List<ProductCategory> getProductCategoryList(@Param("shopId") String shopId);
}
