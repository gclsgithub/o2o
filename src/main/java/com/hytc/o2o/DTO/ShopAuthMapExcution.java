package com.hytc.o2o.DTO;

import com.hytc.o2o.entity.ShopAuthMap;
import com.hytc.o2o.enums.ShopAuthMapStateEnum;

import java.util.List;

public class ShopAuthMapExcution {
    private Integer state;

    private String stateInfo;

    //授权数
    private Integer count;

    //操作的shopAuthMap
    private ShopAuthMap shopAuthMap;

    private List<ShopAuthMap> shopAuthMapList;

    //无参构造
    public ShopAuthMapExcution(){

    }

    //失败的构造
    public ShopAuthMapExcution(ShopAuthMapStateEnum stateEnum){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    //成功的构造函数
    public ShopAuthMapExcution(ShopAuthMapStateEnum stateEnum, List<ShopAuthMap> shopAuthMapList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shopAuthMapList = shopAuthMapList;
    }

    //成功的构造函数
    public ShopAuthMapExcution (ShopAuthMapStateEnum stateEnum,ShopAuthMap shopAuthMap){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shopAuthMap = shopAuthMap;
    }
}
