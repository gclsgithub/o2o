package com.hytc.o2o.service;

import com.hytc.o2o.entity.ProductRecord;

import java.util.List;

public interface ProductRecordService {

    /**
     * 获取
     * @return List<ProductRecord>
     */
    List<ProductRecord> getAllInfos(String shopId,String useName);
}
