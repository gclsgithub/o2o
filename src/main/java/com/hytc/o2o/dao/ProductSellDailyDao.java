package com.hytc.o2o.dao;

import com.hytc.o2o.entity.ProductSellDaily;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ProductSellDailyDao {

    /**
     * 查处每一天的商品信息
     * @param productSellDaily
     * @param startTime
     * @param endTIme
     * @return
     */
    List<ProductSellDaily> findProductSellDailyList(
            @Param("condition") ProductSellDaily productSellDaily,
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTIme);

    /**
     * 将平台所有的日销量进行痛击
     * @return
     */
    Integer save();

}
