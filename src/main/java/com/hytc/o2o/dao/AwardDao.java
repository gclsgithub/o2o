package com.hytc.o2o.dao;

import com.hytc.o2o.entity.Award;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    @Select("<script>"+
            "SELECT  award_id AS awardId," +
            "        award_name AS awardName," +
            "        award_desc AS awardDesc," +
            "        award_img AS awardImg," +
            "        point AS needPoint," +
            "        create_time AS createTime," +
            "        last_edit_time AS lastEditTime," +
            "        shop_id AS shopId," +
            "        priority,"+
            "        enable_status AS enableStatus"+
            "        FROM " +
            "        tb_award"+
            " WHERE" +
            "<when test='shopId != null'>" +
            "   shop_id = #{shopId}" +
            "</when>'"+
            "<when test='awardName != null'>" +
            "  AND award_name = #{awardName}" +
            "</when>'"+
            "</script>")
    List<Award> findAll(@Param("shopId")String shopId,@Param("awardName")String awardName);
}
