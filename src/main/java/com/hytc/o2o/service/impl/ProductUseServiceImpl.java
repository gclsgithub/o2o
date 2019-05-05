package com.hytc.o2o.service.impl;

import com.hytc.o2o.dao.ProductUseDao;
import com.hytc.o2o.entity.ProductUse;
import com.hytc.o2o.service.ProductUseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductUseServiceImpl implements ProductUseService {


    @Autowired
    private ProductUseDao productUseDao;

    @Override
    public List<ProductUse> getAllInfos(String shopId) {
        List<ProductUse> dbProductUseList = productUseDao.getAll(shopId);


        List<ProductUse> outputList = new ArrayList<>();


        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        for (ProductUse productUse : dbProductUseList) {

            if (CollectionUtils.isEmpty(outputList)) {
                productUse.setWeek(dateFm.format(productUse.getCreateTime()));
                String count = String.valueOf(Integer.valueOf(productUse.getCount()));
                productUse.setCount(count);
                outputList.add(productUse);
                continue;
            }

            Boolean addFlag = Boolean.TRUE;
            for (ProductUse p2 : outputList) {
                if (p2.getProductName().equals(productUse.getProductName()) && p2.getCreateTime().equals(productUse.getCreateTime())) {
                    p2.setWeek(dateFm.format(p2.getCreateTime()));
                    String count = String.valueOf(Integer.valueOf(p2.getCount()) + Integer.valueOf(productUse.getCount()));
                    p2.setCount(count);
                    addFlag = Boolean.FALSE;
                    break;
                }
            }

            if (addFlag) {
                productUse.setWeek(dateFm.format(productUse.getCreateTime()));
                String count = String.valueOf(Integer.valueOf(productUse.getCount()));
                productUse.setCount(count);
                outputList.add(productUse);
            }
        }
        return outputList;
    }
}

