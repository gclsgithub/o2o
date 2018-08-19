package com.hytc.o2o.service;

import com.hytc.o2o.DTO.ProductCategoryExcution;
import com.hytc.o2o.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    /**
     *
     * @param productCategoryList
     * @return
     */
    ProductCategoryExcution batchAndProductCategoery(List<ProductCategory> productCategoryList);

    /**
     * 物理删除 productCategory
     * @param productId
     * @return
     */
    ProductCategoryExcution delProductCategoery(Long productId);
}
