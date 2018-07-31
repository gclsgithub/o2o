package com.hytc.o2o.dao;

import com.hytc.o2o.entity.Shop;
import org.apache.ibatis.annotations.Param;

public interface ShopDao {

    /**
     * 根据ShopId查询单个Shop信息
     * @param shopId
     * @return
     */
    Shop queryShopByShopId(@Param("shopId") Long shopId);
    /**
     * 新增店铺
     * @param shop
     * @return
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺信息
     * @param shop
     * @return
     */
    int updateShop(Shop shop);
}
