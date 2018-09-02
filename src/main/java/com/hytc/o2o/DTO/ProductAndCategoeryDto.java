package com.hytc.o2o.DTO;

import com.hytc.o2o.entity.Product;
import com.hytc.o2o.entity.ProductCategory;

import java.io.Serializable;
import java.util.List;

public class ProductAndCategoeryDto implements Serializable {
    private static final long serialVersionUID = -8177625906693395006L;

    private Product product;;

    private List<ProductCategory> productCategoryList;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<ProductCategory> getProductCategoryList() {
        return productCategoryList;
    }

    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }
}
