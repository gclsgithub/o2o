package com.hytc.o2o.DTO;

import com.hytc.o2o.entity.Product;
import com.hytc.o2o.enums.ProductStateEnum;

import java.io.Serializable;
import java.util.List;

public class ProductExcution implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1078884802689380049L;

    /** 结果状态 */
    private Integer status;

    /** 标识状态 */
    private String stateInfo;

    /** 商品数量 */
    private Integer count;

    /** 操作的product（增删改茶商品使用）*/
    private Product product;

    /**获取的product列表（查询商品列表的时候用） */
    private List<Product> productList;

    public ProductExcution(){

    }

    /** 失败的构造器 */
    public ProductExcution(ProductStateEnum stateEnum){
        this.status = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    /** 成功的构造器 */
    public ProductExcution (ProductStateEnum stateEnum,Product product){
        this.status = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.product = product;
    }

    /** 成功的构造器 */
    public ProductExcution (ProductStateEnum stateEnum,List<Product> productList){
        this.status = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.productList = productList;
    }

    /** 成功的构造器 */
    public ProductExcution (ProductStateEnum stateEnum,Product product,List<Product> productList){
        this.status = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.product = product;
        this.productList = productList;
    }

}
