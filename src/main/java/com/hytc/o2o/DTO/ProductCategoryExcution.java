package com.hytc.o2o.DTO;

import com.hytc.o2o.enums.ProductCategoryEnum;

import java.io.Serializable;

public class ProductCategoryExcution implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -3193274444690657623L;
    /**
     * Enum的状态码
     */
    private Integer status;

    /**
     * Enum的值
     */
    private String statusInfo;

    private ProductCategoryEnum productCategoryEnum;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public ProductCategoryEnum getProductCategoryEnum() {
        return productCategoryEnum;
    }

    public void setProductCategoryEnum(ProductCategoryEnum productCategoryEnum) {
        this.productCategoryEnum = productCategoryEnum;
    }
}
