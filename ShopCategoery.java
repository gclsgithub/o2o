package main.java.com.hytc.o2o.entity;

import java.util.Date;

/**
 * 店铺类别
 * @author hytc
 */
public class ShopCategoery {
    private Long shopCategoeryId;

    private String shopCategoeryName;

    private String shopCategoeryDesc;

    private String shopCateGoeryImg;

    private Integer priority;

    private Date createTime;

    private Date LastEditTime;

    private ShopCategoery shopCategoery;

    public void setShopCategoeryId(Long shopCategoeryId) {
        this.shopCategoeryId = shopCategoeryId;
    }

    public void setShopCategoeryName(String shopCategoeryName) {
        this.shopCategoeryName = shopCategoeryName;
    }

    public void setShopCategoeryDesc(String shopCategoeryDesc) {
        this.shopCategoeryDesc = shopCategoeryDesc;
    }

    public void setShopCateGoeryImg(String shopCateGoeryImg) {
        this.shopCateGoeryImg = shopCateGoeryImg;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        LastEditTime = lastEditTime;
    }

    public void setShopCategoery(ShopCategoery shopCategoery) {
        this.shopCategoery = shopCategoery;
    }

    public Long getShopCategoeryId() {
        return shopCategoeryId;
    }

    public String getShopCategoeryName() {
        return shopCategoeryName;
    }

    public String getShopCategoeryDesc() {
        return shopCategoeryDesc;
    }

    public String getShopCateGoeryImg() {
        return shopCateGoeryImg;
    }

    public Integer getPriority() {
        return priority;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getLastEditTime() {
        return LastEditTime;
    }

    public ShopCategoery getShopCategoery() {
        return shopCategoery;
    }
}
