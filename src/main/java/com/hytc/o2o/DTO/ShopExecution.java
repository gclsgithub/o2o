package com.hytc.o2o.DTO;

import com.hytc.o2o.entity.Shop;
import com.hytc.o2o.enums.ShopStateEnum;

import java.util.List;

/**
 * Shop的DTO
 * @author  hytc
 */
public class ShopExecution {

    /**
     * Enum的状态码
     */
    private Integer status;

    /**
     * Enum的值
     */
    private String statusInfo;

    /**
     * 店家总数
     */
    private Integer shopCount;

    /**
     * 单个店家
     */
    private Shop shop;

    /**
     * 店家列表
     */
    private List<Shop> shopList;


    /**
     * 默认构造函数
     */
    private ShopExecution(){ }


    /**
     * 不含Shop的构造函数
     * @param shopStateEnum
     */
    public ShopExecution(ShopStateEnum shopStateEnum){
        this.status = shopStateEnum.getStatus();
        this.statusInfo = shopStateEnum.getStatusInfo();

    }

    /**
     * 含单个Shop的构造函数
     * @param shopStateEnum
     */
    public ShopExecution(ShopStateEnum shopStateEnum,Shop shop){
        this.status = shopStateEnum.getStatus();
        this.statusInfo = shopStateEnum.getStatusInfo();
        this.shop = shop;
    }

    /**
     * 含ShopList的构造函数
     * @param shopStateEnum
     */
    public ShopExecution(ShopStateEnum shopStateEnum,List<Shop> shops){
        this.status = shopStateEnum.getStatus();
        this.statusInfo = shopStateEnum.getStatusInfo();
        shopList = shops;

    }

}
