package com.hytc.o2o.dao;

import com.hytc.o2o.entity.ProductImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductImgDao {

    /**
     * 查询某个商品的详细图片
     * @param productId 商品Id
     * @return
     */
    List<ProductImg> queryProductImgList(@Param("productId") Long productId);

    /**
     * 批量插入商品详情图
     * @param productImgList
     * @return
     */
    int batchInsertProductImg(@Param("productImgList") List<ProductImg> productImgList);

    /**
     * 根据商品Id删除商品详情图
     * @param productId
     * @return
     */
    int deleteProductImgByProductId(@Param("productId")Long productId);

    /**
     * 根据 商品Id查询商品详情图
     * @param productId 商品名
     * @return
     */
    List<ProductImg> searchProductImg(@Param("productId") Long productId);

    /**
     * 更新商品信息
     * @param productImg
     */
    void updateProductCategoery(@Param("productImg") ProductImg productImg);
}
