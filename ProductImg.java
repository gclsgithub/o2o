package main.java.com.hytc.o2o.entity;

import javax.xml.crypto.Data;

/**
 * 详情图片
 * @author hytcs
 */
public class ProductImg {
    private Long productImgId;

    private String imgAddr;

    private String imgDesc;

    private Integer priority;

    private Data createTime;

    private Long productId;

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

    public void setCreateTime(Data createTime) {
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

    public Data getCreateTime() {
        return createTime;
    }

    public Long getProductId() {
        return productId;
    }
}
