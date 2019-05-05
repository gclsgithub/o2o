package com.hytc.o2o.service;

import com.hytc.o2o.entity.HeadLine;
import com.hytc.o2o.entity.UserPoint;

import java.util.List;

public interface HeadLineService {

    /**
     * 获取在线的头条信息
     *
     * @return
     */
    List<HeadLine> getUpHeadLine();

    /**
     * 获取指定状态的头条信息
     *
     * @param status
     * @return
     */
    List<HeadLine> getAllHeadLines(String status);


    List<UserPoint> getTbUserProductInfo(Long userId);
}
