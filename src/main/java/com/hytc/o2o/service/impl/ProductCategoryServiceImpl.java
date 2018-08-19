package com.hytc.o2o.service.impl;


import com.hytc.o2o.DTO.ProductCategoryExcution;
import com.hytc.o2o.dao.ProductCategoryDao;
import com.hytc.o2o.entity.ProductCategory;
import com.hytc.o2o.enums.ProductCategoryEnum;
import com.hytc.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            productCategoryDao.insertProductCategoryList(productCategoryList);
        }catch (Exception ex1){
            productCategoryExcution.setProductCategoryEnum(ProductCategoryEnum.INSERT_ERROR);
            return productCategoryExcution;
        }
        productCategoryExcution.setProductCategoryEnum(ProductCategoryEnum.SUCCESS);
        return productCategoryExcution;
    }

    /**
     *
     * @param productId
     * @return
     */
    @Transactional
    @Override
    public ProductCategoryExcution delProductCategoery(Long productId) {
        ProductCategoryExcution productCategoryExcution = new ProductCategoryExcution();
        try {
            productCategoryDao.delProductCategoeryByProductId(productId);
        }catch (Exception e){
            productCategoryExcution.setProductCategoryEnum(ProductCategoryEnum.DELETE_ERROR);
            return productCategoryExcution;
        }
        productCategoryExcution.setProductCategoryEnum(ProductCategoryEnum.SUCCESS);
        return productCategoryExcution;
    }
}
