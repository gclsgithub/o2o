package com.hytc.o2o.enums;

public enum ShopAuthMapStateEnum {
    SUCCESS(0,"创建成功"),INSERT_ERROR(-1001,"创建失败"),EMPTY(-1002,"查找的信息不存在"),DELETE_ERROR(-1003,"删除失败");
    private int state;

    private String stateInfo;

    ShopAuthMapStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }
}
