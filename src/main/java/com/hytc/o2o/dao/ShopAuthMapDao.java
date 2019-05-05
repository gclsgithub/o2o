package com.hytc.o2o.dao;

import com.hytc.o2o.entity.ShopAuthMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopAuthMapDao {

    /**
     * 分页查处店铺下面的授权信息
     *
     * @param shopId
     * @param start
     * @param pageSize
     */
    List<ShopAuthMap> findeShopAuthMapListByshopId(@Param("shopId") long shopId,
                                                   @Param("start") int start,
                                                   @Param("pageSize") int pageSize);

    /**
     * 获取授权总数
     *
     * @param shopId
     */
    int findShopAuthContByShopId(@Param("shopId") long shopId);


    /**
     * 增加一条授权信息
     *
     * @param shopAuthMap
     * @return key 返回插入的主键
     */
    int save(@Param("condition") ShopAuthMap shopAuthMap);

    /**
     * 修改一条授权信息
     *
     * 删除员工授权也是通过该方法，只有把状态职位不可用就行
     *
     * @param shopAuthMap
     * @return
     */
    int updateShopAuth(@Param("condition") ShopAuthMap shopAuthMap);

    /**
     * 根据主键查处授权信息
     *
     * @param shopAuthId
     */
    ShopAuthMap findShopAurhMapById(@Param("shopAuthId")Long shopAuthId);

    /**
     * 新规登陆
     * @param shopAuthMap
     * @return
     */
    int insertIntoShopMap(@Param("condition") ShopAuthMap shopAuthMap);

    /**
     * 根据userName找到唯一标示Id
     * @param name
     * @return
     */
    List<String> findUserIdByName(@Param("name") String name);
}
