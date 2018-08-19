package com.hytc.o2o.service;

import com.hytc.o2o.DTO.ProductCategoryExcution;
import com.hytc.o2o.entity.ProductCategory;

import java.util.List;

public interface ProductService {
    /**
     * 获取产品种类列表
     * @param shopId
     * @return List<ProductCategory>
     */
    List<ProductCategory> getProductCategoeryList(String shopId);

    /**
     * 按照条件查询Product
     * @param productId
     * @return
     */
    ProductCategoryExcution getProduct(Long productId);
}
