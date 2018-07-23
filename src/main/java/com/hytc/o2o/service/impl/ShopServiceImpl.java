package com.hytc.o2o.service.impl;

import com.hytc.o2o.DTO.ShopExecution;
import com.hytc.o2o.dao.ShopDao;
import com.hytc.o2o.entity.Shop;
import com.hytc.o2o.enums.ShopStateEnum;
import com.hytc.o2o.exceptions.ShopRuntimeException;
import com.hytc.o2o.service.ShopService;
import com.hytc.o2o.util.ImageUtil;
import com.hytc.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Date;

/**
 * @author hytc
 */
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg) {

        if (shop == null ){
            return new ShopExecution(ShopStateEnum.NULL_SHOPID);
        }

        shop.setEnableStatus(1);
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());

        //这次更新没有更新商店的图片地址
        Integer effectInsertNum = shopDao.insertShop(shop);

        if (effectInsertNum < 0){
            throw new ShopRuntimeException("插入失败");
        }else{
            //将上传的文件存储到项目之中，同时把相对对路径存储的Shop对象之中，在更新到数据库
            addFile2Proctectand2Shop(shop,shopImg);

            Integer effectUpdateNum = shopDao.updateShop(shop);

            if (effectUpdateNum < 0){
                throw  new ShopRuntimeException("更新数量失败");
            }
        }
        return new ShopExecution(ShopStateEnum.CHECK,shop);

    }

    /**
     * 将上传的文件存储到项目之中，同时把相对对路径存储的Shop对象之中，在更新到数据库
     * @param shop
     * @param shopImg
     */
    private void addFile2Proctectand2Shop(Shop shop, CommonsMultipartFile shopImg) {
        //获取存储的相对路径
        String reativePath = PathUtil.getShopImagePath(shop.getShopId());

        String showImageAddr = ImageUtil.generateThumbnail(shopImg,reativePath);

        shop.setShopImg(showImageAddr);
    }



}
