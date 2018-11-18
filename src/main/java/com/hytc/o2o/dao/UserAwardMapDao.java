package com.hytc.o2o.dao;

import com.hytc.o2o.entity.UserAndAwardMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserAwardMapDao {

    /**
     * 查询用户兑换奖品的记录
     *
     * @param userAndAwardMap
     * @param start
     * @param pageSize
     * @return List
     */
    List<UserAndAwardMap> findUserAwardMapList(@Param("condition") UserAndAwardMap userAndAwardMap,
                                               @Param("start") Integer start,
                                               @Param("pageSize") Integer pageSize);

    /**
     * 查处符合条件的数量
     *
     * @param userAndAwardMap
     * @return Integer
     */
    Integer findUserAwardCount(@Param("condition") UserAndAwardMap userAndAwardMap);

    /**
     * 根据ID查处对应的UserAndAAward对象
     *
     * @param userAndAwardId
     * @return UserAndAwardMap
     */
    UserAndAwardMap findUserAndAwardById(@Param("id") String userAndAwardId);

    /**
     * 保存用户奖品信息
     *
     * @param userAndAwardMap
     * @return
     */
    Integer sava(@Param("condition") UserAndAwardMap userAndAwardMap);

    /**
     * 更新某个用户领取奖品
     *
     * @param userAndAwardMap
     * @return Integer
     */
    Integer updateUserAndAward(@Param("condition") UserAndAwardMap userAndAwardMap);

    /**
     * 根据Id删除某个用户表信息
     *
     * @param userAndAwardId
     * @return Integer
     */
    Integer delUserAndAward(@Param("id") String userAndAwardId);
}
