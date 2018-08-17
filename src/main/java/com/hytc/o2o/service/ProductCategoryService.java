package com.hytc.o2o.service;

import com.hytc.o2o.entity.ProductCategory;
import com.hytc.o2o.enums.ProductCategoryEnum;

import java.util.List;

public interface ProductCategoryService {
    /**
     *
     * @param productCategoryList
     * @return
     */
    ProductCategoryEnum batchAndProductCategoery(List<ProductCategory> productCategoryList);
}
