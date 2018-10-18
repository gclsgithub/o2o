package com.hytc.o2o.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品类别
 * @author  hytc
 */
public class ProductCategory implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8541661596024281832L;

    private Long productionCategoryId;

    private Long shopId;

    private String productionCategoryName;

    private int priority;

    private Date createTime;

    private String enableStatus;

    private String productId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(String enableStatus) {
        this.enableStatus = enableStatus;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getProductionCategoryId() {
        return productionCategoryId;
    }

    public void setProductionCategoryId(Long productionCategoryId) {
        this.productionCategoryId = productionCategoryId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getProductionCategoryName() {
        return productionCategoryName;
    }

    public void setProductionCategoryName(String productionCategoryName) {
        this.productionCategoryName = productionCategoryName;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
