package com.hytc.o2o.entity;

import com.sun.istack.internal.NotNull;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 本地账户
 *
 * @author hytc
 */
public class LocalAuth {

    private Long localAuthID;

    private String userName;

    private String passWord;

    private LocalDateTime crateTime;

    private Date lastEditTime;

    private PersonInfo personInfo;

    private String userType;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setLocalAuthID(Long localAuthID) {
        this.localAuthID = localAuthID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setCrateTime(LocalDateTime crateTime) {
        this.crateTime = crateTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }

    public Long getLocalAuthID() {
        return localAuthID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public LocalDateTime getCrateTime() {
        return crateTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }
}
