package com.hytc.o2o.entity;

import java.util.Date;

/**
 * 顾客已经领取的奖品的映射
 */
public class UserAndAwardMap {

    private Long userAndAwardId;

    private Date createTime;

    /**
     * 判断是逗兑换
     */
    private  Integer usedStatus;

    /**
     * 领取奖品所消耗的积分
     */
    private Integer point;

    /**
     * 用户信息
     */
    private PersonInfo user;

    private Award award;

    /**
     * 操作员信息
     */
    private PersonInfo operator;

    public Long getUserAndAwardId() {
        return userAndAwardId;
    }

    public void setUserAndAwardId(Long userAndAwardId) {
        this.userAndAwardId = userAndAwardId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUsedStatus() {
        return usedStatus;
    }

    public void setUsedStatus(Integer usedStatus) {
        this.usedStatus = usedStatus;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public PersonInfo getUser() {
        return user;
    }

    public void setUser(PersonInfo user) {
        this.user = user;
    }

    public Award getAward() {
        return award;
    }

    public void setAward(Award award) {
        this.award = award;
    }

    public PersonInfo getOperator() {
        return operator;
    }

    public void setOperator(PersonInfo operator) {
        this.operator = operator;
    }
}
