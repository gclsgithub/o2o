package com.hytc.o2o.dao;

import com.hytc.o2o.entity.HeadLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 头条信息查询
 */
public interface HeadLineDao {

    /**
     * 查询指定状态下的头条信息
     * @param headLine
     * @return
     */
    List<HeadLine> queryHeadLineNotDel(@Param("headLine") HeadLine headLine);

    /**
     * 根据Id查询头条信息
     * @param headLine
     * @return
     */
    HeadLine queryHeadLineById(@Param("headLine") HeadLine headLine);

    /**
     * 根据Id查询头条信息
     * @param list
     * @return
     */
    List<HeadLine> queryHeadLineByIds(@Param("list") List<HeadLine> list);
}
