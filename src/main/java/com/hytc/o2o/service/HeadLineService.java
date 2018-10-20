package com.hytc.o2o.service;

import com.hytc.o2o.entity.HeadLine;

import java.util.List;

public interface HeadLineService {

    /**
     * 获取在线的头条信息
     * @return
     */
    List<HeadLine> getHeadLine();
}
