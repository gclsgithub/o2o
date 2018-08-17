package com.hytc.o2o.entity;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 地区实体
 * @author hytc
 */
public class Area {

    //ID
    private Integer areaId;

    //名称
    private String areaName;

    //权重
    private Integer priority;

    //创建时间
    private Data createTime;

    //最后编辑时间
    private Date lastEditTime;

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setCreateTime(Data createTime) {
        this.createTime = createTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public Integer getPriority() {
        return priority;
    }

    public Data getCreateTime() {
        return createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }
}
