package com.hytc.o2o.entity;

import java.util.Date;

/**
 * 店铺授权相关的类
 */
public class ShopAuthMap {

    private Long shopAuthId;

    /**
     * 职位
     */
    private String jobName;

    /**
     * 支撑符号（用于权限控制）
     */
    private Integer titleFlag;

    /**
     * 授权状态 0 无效  1有效
     */
    private Integer emableStatus;

    private Date createTime;

    private Date lastEditTime;

    private PersonInfo emmployee;

    private Shop shop;

    public Integer getEmableStatus() {
        return emableStatus;
    }

    public void setEmableStatus(Integer emableStatus) {
        this.emableStatus = emableStatus;
    }

    public Long getShopAuthId() {
        return shopAuthId;
    }

    public void setShopAuthId(Long shopAuthId) {
        this.shopAuthId = shopAuthId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Integer getTitleFlag() {
        return titleFlag;
    }

    public void setTitleFlag(Integer titleFlag) {
        this.titleFlag = titleFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public PersonInfo getEmmployee() {
        return emmployee;
    }

    public void setEmmployee(PersonInfo emmployee) {
        this.emmployee = emmployee;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
