package com.hytc.o2o.service;

import com.hytc.o2o.DTO.ShopAuthMapExcution;
import com.hytc.o2o.entity.ShopAuthMap;

public interface ShopAuthMapService {

    /**
     * 根据店铺Id显示授权信息
     *
     * @param shopId
     * @param start
     * @param pageSize
     * @return
     */
    ShopAuthMapExcution listShopAuthMapByShopId(Long shopId, Integer start, Integer pageSize);

    /**
     * 根据shopAuthId返回对应的授权信息
     *
     * @param shopAuthId
     * @return
     */
    ShopAuthMapExcution getShopAuthMapByKeyId(Long shopAuthId);

    /**
     * 添加授权信息
     *
     * @param shopAuthMap
     * @return
     */
    ShopAuthMapExcution addShopAuthMap(ShopAuthMap shopAuthMap);

    /**
     * 更新授权title
     * 更新enableStatus
     * @param shopAuthMap
     * @return
     */
    ShopAuthMapExcution updateShopAuthMap(ShopAuthMap shopAuthMap);
}
