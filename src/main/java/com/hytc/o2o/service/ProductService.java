package com.hytc.o2o.service;

import com.hytc.o2o.DTO.ImageHolder;
import com.hytc.o2o.DTO.ProductAndCategoeryDto;
import com.hytc.o2o.DTO.ProductExcution;
import com.hytc.o2o.entity.*;
import com.hytc.o2o.exceptions.ProductRuntimeException;

import java.io.InputStream;
import java.util.List;

public interface ProductService {
    /**
     * 获取产品种类列表
     * @return List<ProductCategory>
     */
    List<ProductCategory> getProductCategoeryList();

    /**
     * 按照条件查询Product
     * @param productId
     * @return
     */
    ProductAndCategoeryDto getProduct(Long productId);

    /**
     * 添加商品
     * @param product 商品信息
     * @param thumnail 缩略图
     * @param productImgList 详情图
     * @return
     * @throws ProductRuntimeException
     */
    ProductExcution addProduct(Product product, ImageHolder thumnail, List<ImageHolder> productImgList)throws ProductRuntimeException;

    /**
     * 删除商品信息
     * @param productId
     */
    void delProduct(String productId,String status);

    /**
     * 根据ShopId获得Product的数据
     * @param shopId
     * @param index
     * @param pageSize
     * @return
     */
    List<Product> getProductByShopId(Long shopId,int index ,int pageSize);

    /**
     * 按照条件搜索商品信息
     *
     * @param product
     * @param index
     * @param pageSize
     * @return
     */
    List<Product> getProductList(Product product, int index, int pageSize);

    /**
     * 插入销售数据
     *
     * @param productSellDaily
     * @return
     */
    int createProductSellInfo(ProductSellDaily productSellDaily, LocalAuth user );
}
