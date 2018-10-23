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

    /**
     * 查询Categoery的详细信息
     * @param productionCategoryId
     * @return
     */
    ProductCategory searchProductCategoeryByProductCategoeryId(Long productionCategoryId);

    /**
     * 更新商品类别
     * @param productCategory
     * @return
     */
    void updateProductCategoery(ProductCategory productCategory);

    /**
     * 获取
     * @param shopCategoeryId
     * @return
     */
    List<ProductCategory> searchProductCategoeryList(Long shopCategoeryId);
}
