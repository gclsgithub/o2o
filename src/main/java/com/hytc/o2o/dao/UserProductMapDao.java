package com.hytc.o2o.dao;

import com.hytc.o2o.entity.UserProductMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserProductMapDao {

    /**
     * 分页查询用户购买商品的记录的所有记录
     *
     * @param userProductMap
     * @param start
     * @param pageSIze
     * @return
     */
    List<UserProductMap> findUserProductMapList(@Param("condition")UserProductMap userProductMap,
                                                @Param("start")Integer start,
                                                @Param("pageSize")Integer pageSIze);

    /**
     * 查询用户购买商品的总件数
     * @param userProductMap
     * @return
     */
    Integer queryUserProductCount(@Param("condition")UserProductMap userProductMap);

    /**
     * 添加一条详细信息
     * @param userProductMap
     * @return
     */
    Integer save(@Param("condition")UserProductMap userProductMap);

}
