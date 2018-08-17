package com.hytc.o2o.entity;

import java.util.Date;

/**
 * 店铺实体
 * @author hytc
 *
 */
public class Shop  {
    private Long shopId;

    private String shopName;

    private String shopDesc;

    private String shopAddr;

    private String phone;

    private String shopImg;

    private Integer priority;

    private Date createTime;

    private Date lastEditTime;

    // -1 不可用  0 审核中  1 可用
    private Integer enableStatus;


    //超级管理员给店家得提醒
    private String advice;

    private  Area area;

    private PersonInfo owner;

    private ShopCategoery shopCategoery;



    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setShopDesc(String shopDesc) {
        this.shopDesc = shopDesc;
    }

    public void setShopAddr(String shopAddr) {
        this.shopAddr = shopAddr;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setShopImg(String shopImg) {
        this.shopImg = shopImg;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public void setOwner(PersonInfo owner) {
        this.owner = owner;
    }

    public void setShopCategoery(ShopCategoery shopCategoery) {
        this.shopCategoery = shopCategoery;
    }





    public String getShopName() {
        return shopName;
    }

    public String getShopDesc() {
        return shopDesc;
    }

    public String getShopAddr() {
        return shopAddr;
    }

    public String getPhone() {
        return phone;
    }

    public String getShopImg() {
        return shopImg;
    }

    public Integer getPriority() {
        return priority;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public String getAdvice() {
        return advice;
    }

    public Area getArea() {
        return area;
    }

    public PersonInfo getOwner() {
        return owner;
    }

    public ShopCategoery getShopCategoery() {
        return shopCategoery;
    }
}
