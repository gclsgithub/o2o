package com.hytc.o2o.service.impl;


import com.hytc.o2o.DTO.ProductCategoryExcution;
import com.hytc.o2o.dao.ProductCategoryDao;
import com.hytc.o2o.entity.ProductCategory;
import com.hytc.o2o.enums.ProductCategoryEnum;
import com.hytc.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Transactional
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    @Transactional
    public ProductCategoryExcution batchAndProductCategoery(List<ProductCategory> productCategoryList) {
        ProductCategoryExcution productCategoryExcution = new ProductCategoryExcution();
        try {
            String productId = null;

            if (!CollectionUtils.isEmpty(productCategoryList)){
                productId = productCategoryList.get(0).getProductId();
            }
            productCategoryDao.insertProductCategoryList(productCategoryList);
        }catch (Exception ex1){
            productCategoryExcution.setProductCategoryEnum(ProductCategoryEnum.INSERT_ERROR);
            return productCategoryExcution;
        }
        productCategoryExcution.setProductCategoryEnum(ProductCategoryEnum.SUCCESS);
        return productCategoryExcution;
    }

    /**
     * 逻辑删除某个分类
     * @param productCategoeryId
     * @return
     */
    @Transactional
    @Override
    public ProductCategoryExcution delProductCategoery(Long productCategoeryId) {
        ProductCategoryExcution productCategoryExcution = new ProductCategoryExcution();
        try {
            //逻辑删除
            productCategoryDao.delProductCategoeryByProductCategoeryId(productCategoeryId);
        }catch (Exception e){
            productCategoryExcution.setProductCategoryEnum(ProductCategoryEnum.DELETE_ERROR);
            return productCategoryExcution;
        }
        productCategoryExcution.setProductCategoryEnum(ProductCategoryEnum.SUCCESS);
        return productCategoryExcution;
    }

    /**
     * 搜索一个分类信息
     * @param productionCategoryId
     * @return
     */
    @Override
    public ProductCategory searchProductCategoeryByProductCategoeryId(Long productionCategoryId) {

        return productCategoryDao.searchProductCategoeryIdByProductCategoeryId(productionCategoryId);
    }
}
