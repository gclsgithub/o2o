package com.hytc.o2o.entity;


import java.time.LocalDateTime;

/**
 * 详情图片
 * @author hytcs
 */
public class ProductImg {
    private Long productImgId;

    //详情图地址
    private String imgAddr;

    private String imgDesc;

    private Integer priority;

    private LocalDateTime createTime;

    private Long productId;

    //0下架 1.可以在前台系统展示

    public void setProductImgId(Long productImgId) {
        this.productImgId = productImgId;
    }

    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr;
    }

    public void setImgDesc(String imgDesc) {
        this.imgDesc = imgDesc;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductImgId() {

        return productImgId;
    }

    public String getImgAddr() {
        return imgAddr;
    }

    public String getImgDesc() {
        return imgDesc;
    }

    public Integer getPriority() {
        return priority;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public Long getProductId() {
        return productId;
    }
}
