package main.java.com.hytc.o2o.entity;

import java.util.Date;

/**
 * 个人信息
 * @author hytc
 */
public class PersonInfo {
    //Id
    private Long id;

    //姓名
    private String name;

    private String profileImg;

    private String email;

    private String gender;

    private Integer enableStatuus;

    //1--顾客  2--店家  3--超级管理员
    private Integer userType;

    private Date createTime;

    private Date lastEditTime;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEnableStatuus(Integer enableStatuus) {
        this.enableStatuus = enableStatuus;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public Integer getEnableStatuus() {
        return enableStatuus;
    }

    public Integer getUserType() {
        return userType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }
}
