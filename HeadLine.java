package main.java.com.hytc.o2o.entity;

import java.util.Date;

/**
 * 头条实体
 * @author hytc
 */
public class HeadLine {
    private  Long lineId;

    private String lineName;

    private String lineLink;

    private String lineImg;

    private Integer priority;

    //0  可用    1  不可用
    private Integer enableStatus;

    private Date createTime;

    private Date lastEditTime;

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public void setLineLink(String lineLink) {
        this.lineLink = lineLink;
    }

    public void setLineImg(String lineImg) {
        this.lineImg = lineImg;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public Long getLineId() {
        return lineId;
    }

    public String getLineName() {
        return lineName;
    }

    public String getLineLink() {
        return lineLink;
    }

    public String getLineImg() {
        return lineImg;
    }

    public Integer getPriority() {
        return priority;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }
}
