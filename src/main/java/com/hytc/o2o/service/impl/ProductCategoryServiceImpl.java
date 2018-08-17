package com.hytc.o2o.service.impl;


import com.hytc.o2o.dao.ProductCategoryDao;
import com.hytc.o2o.entity.ProductCategory;
import com.hytc.o2o.enums.ProductCategoryEnum;
import com.hytc.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public ProductCategoryEnum batchAndProductCategoery(List<ProductCategory> productCategoryList) {
        Integer count = productCategoryDao.insertProductCategoryList(productCategoryList);

        if (count == productCategoryList.size()){
            return ProductCategoryEnum.SUCCESS;
        }
        return ProductCategoryEnum.INSERT_ERROR;
    }
}
