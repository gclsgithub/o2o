package com.hytc.o2o.dao;

import com.hytc.o2o.entity.ShopCategoery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopCategoeryDao {
    /**
     * 查找所有店铺分类
     *
     * @return ShopCategoeryLists
     */
    List<ShopCategoery> findAlls(@Param("shopCategoeryCondition") ShopCategoery shopCategoery);

    /**
     * 查询所有一级父类的分类 tb_shopCategoery
     *
     * @param parentId
     * @return
     */
    List<ShopCategoery> queryShopCategoeryById(@Param("parentId") String parentId);
}
