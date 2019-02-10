package com.hytc.o2o.service;

import com.hytc.o2o.DTO.ImageHolder;
import com.hytc.o2o.entity.Award;

import java.util.List;

public interface AwardService {
    List<Award> finaAll();

    Award findAwardByAwardId(String awardId);

    Boolean addAward(Award award, ImageHolder holder);
}
