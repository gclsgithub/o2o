package com.hytc.o2o.service.impl;

import com.hytc.o2o.DTO.ShopAuthMapExcution;
import com.hytc.o2o.dao.ShopAuthMapDao;
import com.hytc.o2o.entity.ShopAuthMap;
import com.hytc.o2o.enums.ShopAuthMapStateEnum;
import com.hytc.o2o.service.ShopAuthMapService;
import com.hytc.o2o.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

@Service
public class ShopAuthMapServiceImpl implements ShopAuthMapService {

    @Autowired
    private ShopAuthMapDao shopAuthMapDao;

    @Override
    public ShopAuthMapExcution listShopAuthMapByShopId(Long shopId, Integer index, Integer pageSize) {
        ShopAuthMapExcution shopAuthMapExcution = null;
        if (!ObjectUtils.isEmpty(shopId) && !ObjectUtils.isEmpty(index) && !ObjectUtils.isEmpty(pageSize)) {

            //get--begin
            int begin = PageUtil.caculate(index, pageSize);

            int count = shopAuthMapDao.findShopAuthContByShopId(shopId);
            if (count >= 1) {
                List<ShopAuthMap> shopAuthMapList
                        = shopAuthMapDao.findeShopAuthMapListByshopId(shopId, begin, pageSize);

                shopAuthMapExcution = new ShopAuthMapExcution(ShopAuthMapStateEnum.SUCCESS, shopAuthMapList);
            } else {
                shopAuthMapExcution = new ShopAuthMapExcution(ShopAuthMapStateEnum.EMPTY);
            }
        }
        return shopAuthMapExcution;
    }

    @Override
    public ShopAuthMapExcution getShopAuthMapByKeyId(Long shopAuthId) {

        ShopAuthMap shopAuthMap = shopAuthMapDao.findShopAurhMapById(shopAuthId);
        ShopAuthMapExcution shopAuthMapExcution = null;
        if (!ObjectUtils.isEmpty(shopAuthMap)) {
            shopAuthMapExcution = new ShopAuthMapExcution(ShopAuthMapStateEnum.SUCCESS, shopAuthMap);
            return shopAuthMapExcution;
        }
        shopAuthMapExcution = new ShopAuthMapExcution(ShopAuthMapStateEnum.EMPTY);

        return shopAuthMapExcution;
    }

    @Override
    @Transactional
    public ShopAuthMapExcution addShopAuthMap(ShopAuthMap shopAuthMap) {
        ShopAuthMapExcution shopAuthMapExcution = null;

        if (ObjectUtils.isEmpty(shopAuthMap)
                && ObjectUtils.isEmpty(shopAuthMap.getShop())
                && ObjectUtils.isEmpty(shopAuthMap.getShop().getShopId())
                && ObjectUtils.isEmpty(shopAuthMap.getEmmployee())
                && ObjectUtils.isEmpty(shopAuthMap.getEmmployee().getUserId())) {

            shopAuthMap.setCreateTime(new Date());
            shopAuthMap.setLastEditTime(new Date());
            shopAuthMap.setEmableStatus(1);
            shopAuthMap.setTitleFlag(0);

            int count = shopAuthMapDao.save(shopAuthMap);

            if (count == 0) {
                shopAuthMapExcution = new ShopAuthMapExcution(ShopAuthMapStateEnum.EMPTY);
                return shopAuthMapExcution;
            } else {
                shopAuthMapExcution = new ShopAuthMapExcution(ShopAuthMapStateEnum.SUCCESS, shopAuthMap);
                return shopAuthMapExcution;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public ShopAuthMapExcution updateShopAuthMap(ShopAuthMap shopAuthMap) {
        ShopAuthMapExcution shopAuthMapExcution = null;
        if (!ObjectUtils.isEmpty(shopAuthMap)
                && !ObjectUtils.isEmpty(shopAuthMap.getShopAuthId())) {
            int isUpdate = shopAuthMapDao.updateShopAuth(shopAuthMap);
            if (isUpdate == 0) {
                shopAuthMapExcution = new ShopAuthMapExcution(ShopAuthMapStateEnum.EMPTY);
                return shopAuthMapExcution;
            } else {
                shopAuthMapExcution = new ShopAuthMapExcution(ShopAuthMapStateEnum.SUCCESS, shopAuthMap);
                return shopAuthMapExcution;
            }
        }
        return shopAuthMapExcution;
    }
}
