package com.hytc.o2o.service.impl;


import com.hytc.o2o.DTO.ImageHolder;
import com.hytc.o2o.dao.ProductDao;
import com.hytc.o2o.dao.ShopCategoeryDao;
import com.hytc.o2o.dao.ShopDao;
import com.hytc.o2o.DTO.ShopExecution;
import com.hytc.o2o.entity.*;
import com.hytc.o2o.enums.ShopStateEnum;
import com.hytc.o2o.exceptions.ShopRuntimeException;
import com.hytc.o2o.service.ShopService;
import com.hytc.o2o.util.ImageUtil;
import com.hytc.o2o.util.PageCalculator;
import com.hytc.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author hytc
 */
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private ShopCategoeryDao shopCategoeryDao;

    @Autowired
    private ProductDao productDao;

    /**
     * 查询前段界面的ShopLit
     *
     * @param shop
     * @param index
     * @param pageSize
     * @return
     */
    @Override
    public ShopExecution getFrontShopList(Shop shop, int index, int pageSize) {
        ShopExecution shopExecution = new ShopExecution();
        Integer currentPageIndex = PageCalculator.caculatateNowPageIndex(index, pageSize);
        Integer count = shopDao.queryShopCount(shop);
        List<Shop> shopList = shopDao.showFrontShopList(shop, currentPageIndex, pageSize);
        if (shopList != null) {
            shopExecution.setShopList(shopList);
            shopExecution.setShopCount(count);
        } else {
            shopExecution.setStatus(ShopStateEnum.INNERERROR.getStatus());
        }
        return shopExecution;
    }

    /**
     * 根据Shop获取Product
     *
     * @param shopId
     * @return
     */
    @Override
    public List<ProductCategory> getProductCategoeryId(Long shopId) {

        return  productDao.getProductCategoryListByShopId(shopId);

    }

    /**
     * 查询后端界面的ShopList
     *
     * @param ShopCondition
     * @param indexNum
     * @param pageSize
     * @return
     */
    @Override
    public ShopExecution getShopList(Shop ShopCondition, int indexNum, int pageSize) {
        ShopExecution shopExecution = new ShopExecution();
        Integer currentPageIndex = PageCalculator.caculatateNowPageIndex(indexNum, pageSize);
        Integer count = shopDao.queryShopCount(ShopCondition);
        List<Shop> shopList = shopDao.queryShopBySomeCondition(ShopCondition, currentPageIndex, pageSize);
        if (shopList != null) {
            shopExecution.setShopList(shopList);
            shopExecution.setShopCount(count);
        } else {
            shopExecution.setStatus(ShopStateEnum.INNERERROR.getStatus());
        }
        return shopExecution;
    }

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, ImageHolder shopImgInputStream) {

        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOPID);
        }
        //手动设置UserId
        String userId = "1";
        PersonInfo owner = new PersonInfo();
        owner.setUserId(new Long(userId));
        shop.setOwner(owner);

        shop.setEnableStatus(1);
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());

        //这次更新没有更新商店的图片地址
        Integer effectInsertNum = shopDao.insertShop(shop);

        if (effectInsertNum < 0) {
            throw new ShopRuntimeException("插入失败");
        } else {
            //将上传的文件存储到项目之中，同时把相对对路径存储的Shop对象之中，在更新到数据库
            addFile2Proctectand2Shop(shop, shopImgInputStream.getImage(), shopImgInputStream.getImageName());

            Integer effectUpdateNum = shopDao.updateShop(shop);

            if (effectUpdateNum < 0) {
                throw new ShopRuntimeException("更新数量失败");
            }
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);

    }

    @Override
    public ShopExecution updateShop(Long shopId, Shop shop) {
        shop.setShopId(shopId);
        int num = shopDao.updateShop(shop);
        if (num != 1) {
            throw new ShopRuntimeException("更新数量失败");
        }
        Shop updateShop = shopDao.queryShopByShopId(shopId);

        if (updateShop.getEnableStatus() == -1) {
            return new ShopExecution(ShopStateEnum.OFFLINE, shop);
        } else if (updateShop.getEnableStatus() == 0) {
            return new ShopExecution(ShopStateEnum.CHECK, shop);
        } else if (updateShop.getEnableStatus() == 1) {
            return new ShopExecution(ShopStateEnum.SUCCESS, shop);
        } else {
            return new ShopExecution(ShopStateEnum.INNERERROR, shop);
        }
    }

    @Override
    public Shop findSingleShopByShopId(Long shopId) {
        return shopDao.queryShopByShopId(shopId);
    }

    @Override
    public List<Product> showShopProductionList(Integer shopId) {
        return shopDao.queryProductionList(shopId);
    }

    /**
     * 查询所有一级父类
     *
     * @return
     */
    @Override
    public List<ShopCategoery> getShopCategoeryList() {
        String parentId = "-1";
        return shopCategoeryDao.queryShopCategoeryById(parentId);
    }

    /**
     * 将上传的文件存储到项目之中，同时把相对对路径存储的Shop对象之中，在更新到数据库
     *
     * @param shop
     * @param shopImgInputStream
     * @param fileName
     */
    private void addFile2Proctectand2Shop(Shop shop, InputStream shopImgInputStream, String fileName) {
        //获取存储的相对路径
        String reativePath = PathUtil.getShopImagePath(shop.getShopId());

        String showImageAddr = ImageUtil.generateThumbnail(shopImgInputStream, reativePath, fileName);

        shop.setShopImg(showImageAddr);
    }


}
