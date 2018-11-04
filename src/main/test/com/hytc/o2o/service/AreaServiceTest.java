package com.hytc.o2o.service;

import com.hytc.o2o.BaseTest;

import com.hytc.o2o.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AreaServiceTest extends BaseTest {

    @Autowired
    private AreaService areaService;

    @Autowired
    private CacheService cacheService;

    @Test
    public void testGetAreaList(){
       List<Area> areaList = areaService.findAlls();
       assertEquals(2,areaList.size());
        cacheService.removeFromCache("arealist");
    }
}
