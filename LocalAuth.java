package main.java.com.hytc.o2o.entity;

import java.util.Date;

/**
 * 本地账户
 * @author hytc
 */
public class LocalAuth {

    private  Long localAuthID;

    private String userName;

    private String passWord;

    private Date crateTime;

    private Date lastEditTime;

    private PersonInfo personInfo;

    public void setLocalAuthID(Long localAuthID) {
        this.localAuthID = localAuthID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setCrateTime(Date crateTime) {
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

    public Date getCrateTime() {
        return crateTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }
}
