package com.hytc.o2o.dao;

import com.hytc.o2o.entity.UserShopMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 积分类
 */
public interface UserShopMapDao {

    /**
     * 查找用户在某个商店下的积分
     *
     * @param userShopMap
     * @param start
     * @param pageSize
     * @return
     */
    List<UserShopMap> findUserShopMapLists(@Param("condition") UserShopMap userShopMap,
                                           @Param("start") Integer start,
                                           @Param("pageSize") Integer pageSize);

    /**
     * 查找数量
     *
     * @param userShopMap
     * @return
     */
    Integer findUserShopMapCount(@Param("condition") UserShopMap userShopMap);

    /**
     * 通过Id查找记录
     *
     * @param userShopMapId
     */
    UserShopMap findUserShopMapByID(@Param("id") Long userShopMapId);

    /**
     * 更新消费记录信息
     *
     * @param userShopMap
     * @return
     */
    Integer updateUserShopMap(@Param("condition") UserShopMap userShopMap);

    /**
     * 插入一条积分信息
     *
     * @param userShopMap
     * @return
     */
    Integer save(@Param("condition") UserShopMap userShopMap);

    /**
     * 删除一条积分信息
     *
     * @param userShopMapId
     * @return
     */
    Integer delUserShopMap(@Param("id")Long userShopMapId);
}
