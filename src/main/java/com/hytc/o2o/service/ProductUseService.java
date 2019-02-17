package com.hytc.o2o.service;

import com.hytc.o2o.entity.ProductUse;

import java.util.List;

public interface ProductUseService {
    /**
     * 获取所有的消费信息
     * @param shopId
     * @return
     */
    List<ProductUse> getAllInfos(String shopId);
}
