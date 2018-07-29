package com.hytc.o2o.service;

import com.hytc.o2o.entity.Area;
import com.hytc.o2o.entity.ShopCategoery;

import java.util.List;

public interface AreaService {

    /**
     * 查找所有的Area信息
     * @return areaList
     */
    List<Area> findAlls();
}
