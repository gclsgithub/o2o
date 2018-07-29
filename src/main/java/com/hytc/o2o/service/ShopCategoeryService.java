package com.hytc.o2o.service;

import com.hytc.o2o.entity.ShopCategoery;

import java.util.List;

public interface ShopCategoeryService {

    /**
     * 查找所有的商店分类
     * parentCategoeryId 是根据父亲的Id查找对应的子分类
     * @param  parentCategoeryId
     * @return ShopCategoeryLists
     */
    List<ShopCategoery> findAlls(ShopCategoery parentCategoeryId);
}
