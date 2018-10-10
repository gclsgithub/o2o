package com.hytc.o2o.enums;

public enum ProductStateEnum {
    SUCCESS(0,"创建成功"),INSERT_ERROR(-1001,"创建失败"),EMPTY(-1002,"区域信息为空"),DELETE_ERROR(-1003,"删除失败");
    private int state;

    private String stateInfo;

    /**
     * 私有构造方法
     * @param state
     * @param stateInfo
     */
    private ProductStateEnum(int state,String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    private static ProductStateEnum stateOf(int state){
        for (ProductStateEnum stateEnum:values()){
            if (stateEnum.getState() == state){
                return stateEnum;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
}
