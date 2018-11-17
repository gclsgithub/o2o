package com.hytc.o2o.service.impl;

import com.hytc.o2o.dao.HeadLineDao;
import com.hytc.o2o.entity.HeadLine;
import com.hytc.o2o.service.HeadLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeadLineServiceImpl implements HeadLineService {

    @Autowired
    HeadLineDao headLineDao;

    @Override
    public List<HeadLine> getUpHeadLine() {
        HeadLine headLine = new HeadLine();
        headLine.setEnableStatus(0);
        return headLineDao.queryHeadLineNotDel(headLine);
    }

    @Override
    public List<HeadLine> getAllHeadLines(String staus) {
        return headLineDao.queryHeadLines(staus);
    }
}
