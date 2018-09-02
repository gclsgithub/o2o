package com.hytc.o2o.service.impl;

import com.hytc.o2o.DTO.ProductAndCategoeryDto;
import com.hytc.o2o.dao.ProductDao;
import com.hytc.o2o.entity.Product;
import com.hytc.o2o.entity.ProductCategory;
import com.hytc.o2o.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;


    @Override
    public List<ProductCategory> getProductCategoeryList(String shopId) {

        return productDao.getShopProductCategoryList(Long.valueOf(shopId));
    }

    @Override
    public ProductAndCategoeryDto getProduct(Long productId, Long shopId) {

        ProductAndCategoeryDto output = new  ProductAndCategoeryDto();
        if (productId != null) {
            Product product = productDao.getProductInfo(productId);
            output.setProduct(product);
        }
        List<ProductCategory> productCategoryList = productDao.getShopProductCategoryList(shopId);

        output.setProductCategoryList(productCategoryList);

        return output;
    }
}
