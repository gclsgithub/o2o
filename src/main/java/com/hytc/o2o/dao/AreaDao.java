package com.hytc.o2o.dao;

import com.hytc.o2o.entity.Area;
import com.hytc.o2o.entity.ShopCategoery;

import java.util.List;

public interface AreaDao {


    /**
     * 列出区域列表
     * @return areaList
     */
    List<Area> findAlls();
}
