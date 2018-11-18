package com.hytc.o2o.entity;

import java.util.Date;

/**
 * 微信粘糊
 * @author hytc
 */
public class WechatAuth {

    private Long wechatID;

    private String openID;

    private Date createTime;

    private PersonInfo personInfo;

    public WechatAuth() {
    }

    public void setWechatID(Long wechatID) {
        this.wechatID = wechatID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }

    public Long getWechatID() {
        return wechatID;
    }

    public String getOpenID() {
        return openID;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }
}
