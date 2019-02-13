package com.hytc.o2o.service.impl;

import com.hytc.o2o.dao.ProductUseDao;
import com.hytc.o2o.entity.ProductUse;
import com.hytc.o2o.service.ProductUseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductUseServiceImpl implements ProductUseService {


    @Autowired
    private ProductUseDao productUseDao;

    @Override
    public List<ProductUse> getAllInfos() {
        List<ProductUse>  dbProductUseList = productUseDao.getAll();

        Iterator<ProductUse> dbItetator = dbProductUseList.iterator();

        List<ProductUse>  outputList = new ArrayList<>();

        while (dbItetator.hasNext()){
            ProductUse p1 = dbItetator.next();
            SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
            p1.setWeek(dateFm.format(p1.getCreateTime()));
            Iterator<ProductUse> outIterator = outputList.iterator();
            while (outIterator.hasNext()){
                outIterator = outputList.iterator();
                ProductUse p2 = outIterator.next();
                if (p2.getProductName().equals(p1.getProductName())){
                    if (dateFm.format(p1.getCreateTime()).equals(dateFm.format(p2.getCreateTime()))){
                        p1.setWeek(dateFm.format(p1.getCreateTime()));
                        p1.setCount(String.valueOf(Integer.valueOf(p1.getCount() == null?"0":p1.getCount())+1));
                        outputList.add(p1);
                    }
                }
            }
            if (CollectionUtils.isEmpty(outputList)){
                outputList.add(p1);
            }
        }
        return outputList;
    }
}
