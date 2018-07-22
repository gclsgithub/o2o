package com.hytc.o2o.enums;

/**
 * 商品类的枚举类型
 * @author hytc
 */
public enum ShopStateEnum {
    CHECK(0,"审核中"),OFFLINE(-1,"非法商店已下线"),SUCCESS(1,"审核通过"),INNERERROR(-2,"内部错误"),NULL_SHOPID(-1002,"ShopId为空");

    private Integer status;

    private String statusInfo;

    /**
     * 私有构造方法
     * @param status
     * @param statusInfo
     */
    private ShopStateEnum(Integer status,String statusInfo){
        this.status = status;
        this.statusInfo = statusInfo;
    }

    /**
     * 按照传入的值返回对应的枚举类型
     */
    private ShopStateEnum getShopEnum(Integer status){
        for (ShopStateEnum shopStateEnum:values()){
            if(shopStateEnum.getStatus().equals(status)){
                return shopStateEnum;
            }
        }
        return null;
    }

    public Integer getStatus() {
        return status;
    }

    public String getStatusInfo() {
        return statusInfo;
    }
}
