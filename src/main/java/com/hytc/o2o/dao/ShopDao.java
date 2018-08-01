package com.hytc.o2o.dao;

import com.hytc.o2o.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {

    /**
     * 获取查询条件下的总件数
     */
    Integer queryShopCount(@Param("ShopCondition")Shop shop);
    /**
     * 查询条件：店铺名（模糊）,店铺状态，店铺类别， 区域Id，OwnerId
     * 查询商店列表
     * @Param shop 查询条件
     * @Param indexNum 开始条
     * @Param pageSize 一页查询多少条
     */
    List<Shop> queryShopBySomeCondition(@Param("ShopCondition") Shop shop,
                                       @Param("indexNum") int indexNum,
                                       @Param("PageSize") int pageSize);

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
