package com.hytc.o2o.dao;

import com.hytc.o2o.entity.Award;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AwardDao {

    /**
     * 根据条件检索奖品
     * @param awardCondition
     * @param startPage
     * @param pageSize
     * @return List
     */
    List<Award> findAwardsBySomeAwardCondition(@Param("condition")Award awardCondition,
                                               @Param("startPage") Integer startPage,
                                               @Param("pageSize") Integer pageSize);

    /**
     * 检索某个条件下的奖品数量
     * @param condition
     * @return Integer
     */
    Integer findAwardsCount(@Param("condition")Award condition);

    /**
     * 根据Id查询奖品
     * @param awardId
     * @return Award
     */
    Award findAwardById(@Param("awardId") long awardId);

    /**
     * 保存award的信息
     * @param award
     * @return Integer
     */
    Integer sava(@Param("award") Award award);

    /**
     * 更新奖品信息
     * @param award
     * @return
     */
    Integer updateAward(@Param("award") Award award);

    Integer delAward(@Param("awardId")Long awardId,
                     @Param("shopId")Long shopId);
}
