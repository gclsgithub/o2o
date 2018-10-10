package com.hytc.o2o.service;

import com.hytc.o2o.DTO.ImageHolder;
import com.hytc.o2o.DTO.ProductAndCategoeryDto;
import com.hytc.o2o.DTO.ProductExcution;
import com.hytc.o2o.entity.Product;
import com.hytc.o2o.entity.ProductCategory;
import com.hytc.o2o.entity.ProductImg;
import com.hytc.o2o.exceptions.ProductRuntimeException;

import java.io.InputStream;
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
     * @Param shopId 商品Id
     * @return
     */
    ProductAndCategoeryDto getProduct(Long productId, Long shopId);

    /**
     * 添加商品
     * @param product 商品信息
     * @param thumnail 缩略图
     * @param productImgList 详情图
     * @return
     * @throws ProductRuntimeException
     */
    ProductExcution addProduct(Product product, ImageHolder thumnail, List<ImageHolder> productImgList)throws ProductRuntimeException;
}
