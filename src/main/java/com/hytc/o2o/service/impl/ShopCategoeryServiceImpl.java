package com.hytc.o2o.service.impl;

import com.hytc.o2o.dao.ShopCategoeryDao;
import com.hytc.o2o.entity.ShopCategoery;
import com.hytc.o2o.service.ShopCategoeryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hytc
 */
@Service
public class ShopCategoeryServiceImpl implements ShopCategoeryService {

    @Autowired
    private ShopCategoeryDao shopCategoeryDao;

    @Override
    public List<ShopCategoery> findAlls(ShopCategoery parentShopCategoery) {
        List<ShopCategoery> shopCategoeryList= shopCategoeryDao.findAlls(parentShopCategoery);

        return shopCategoeryList;

    }

    @Override
    public List<ShopCategoery> findShopCategoeryByParentId(Long shopCategoeryId) {
        return shopCategoeryDao.queryShopCategoeryById(String.valueOf(shopCategoeryId));
    }
}
