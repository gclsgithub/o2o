package com.hytc.o2o.service;

import com.hytc.o2o.entity.ProductCategory;

import java.util.List;

public interface ProductService {
    /**
     * 获取产品种类列表
     * @param shopId
     * @return List<ProductCategory>
     */
    List<ProductCategory> getProductCategoeryList(String shopId);
}
