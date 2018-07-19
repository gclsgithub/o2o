package main.java.com.hytc.o2o.entity;

import java.util.Date;

/**
 * 商品类别
 * @author  hytc
 */
public class ProductCategory {

    private Long productionCategoryId;

    private Long shopId;

    private String productionCategoryName;

    private Integer priority;

    private Date createTime;

    public void setProductionCategoryId(Long productionCategoryId) {
        this.productionCategoryId = productionCategoryId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public void setProductionCategoryName(String productionCategoryName) {
        this.productionCategoryName = productionCategoryName;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getProductionCategoryId() {

        return productionCategoryId;
    }

    public Long getShopId() {
        return shopId;
    }

    public String getProductionCategoryName() {
        return productionCategoryName;
    }

    public Integer getPriority() {
        return priority;
    }

    public Date getCreateTime() {
        return createTime;
    }
}
