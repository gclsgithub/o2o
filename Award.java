package com.hytc.o2o.entity;

import java.util.Date;
import java.util.Objects;

/**
 * 奖品
 *
 * @Auth hytc
 */
public class Award {

    /**
     * 主键Id
     */
    private Long awardId;

    private String awardName;

    private String awardDesc;

    private String awardImg;

    /**
     * 这个奖品将要消耗的分数
     */
    private Integer needPoint;

    private Integer priority;

    private Date createTime;

    private Date lastEditTime;

    private String enableStatus;

    /**
     * 属于哪个店铺
     */
    private Long shopId;

    public String getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(String enableStatus) {
        this.enableStatus = enableStatus;
    }

    public Award() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Award award = (Award) o;
        return Objects.equals(awardId, award.awardId) &&
                Objects.equals(awardName, award.awardName) &&
                Objects.equals(awardDesc, award.awardDesc) &&
                Objects.equals(awardImg, award.awardImg) &&
                Objects.equals(needPoint, award.needPoint) &&
                Objects.equals(priority, award.priority) &&
                Objects.equals(createTime, award.createTime) &&
                Objects.equals(lastEditTime, award.lastEditTime) &&
                Objects.equals(shopId, award.shopId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(awardId, awardName, awardDesc, awardImg, needPoint, priority, createTime, lastEditTime, shopId);
    }

    public Award(Long awardId, String awardName, String awardDesc, String awardImg, Integer needPoint, Integer priority, Date createTime, Date lastEditTime, Long shopId) {
        this.awardId = awardId;
        this.awardName = awardName;
        this.awardDesc = awardDesc;
        this.awardImg = awardImg;
        this.needPoint = needPoint;
        this.priority = priority;
        this.createTime = createTime;
        this.lastEditTime = lastEditTime;
        this.shopId = shopId;
    }

    public Long getAwardId() {
        return awardId;
    }

    public void setAwardId(Long awardId) {
        this.awardId = awardId;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public String getAwardDesc() {
        return awardDesc;
    }

    public void setAwardDesc(String awardDesc) {
        this.awardDesc = awardDesc;
    }

    public String getAwardImg() {
        return awardImg;
    }

    public void setAwardImg(String awardImg) {
        this.awardImg = awardImg;
    }

    public Integer getNeedPoint() {
        return needPoint;
    }

    public void setNeedPoint(Integer needPoint) {
        this.needPoint = needPoint;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
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

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}
