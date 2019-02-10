package com.hytc.o2o.service.impl;

import com.hytc.o2o.dao.AwardDao;
import com.hytc.o2o.entity.Award;
import com.hytc.o2o.service.AwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AwardServiceImpl implements AwardService {


    @Autowired
    private AwardDao awardDao;

    @Override
    public List<Award> finaAll() {
        return awardDao.findAll();
    }
}
