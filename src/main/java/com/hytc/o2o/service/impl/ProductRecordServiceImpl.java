package com.hytc.o2o.service.impl;

import com.hytc.o2o.dao.Records;
import com.hytc.o2o.entity.ProductRecord;
import com.hytc.o2o.service.ProductRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductRecordServiceImpl implements ProductRecordService {

    @Autowired
    private Records records;

    @Override
    public List<ProductRecord> getAllInfos(String shopId,String useName) {
        return records.getAllRecords(shopId,useName);
    }
}
