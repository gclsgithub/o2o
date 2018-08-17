package com.hytc.o2o.enums;

public enum ProductCategoryEnum {
    SUCCESS(0,"创建成功"),INSERT_ERROR(-1001,"创建失败"),EMPTY(-1002,"区域信息为空");
    private int state;

    private String staeInfo;

    private ProductCategoryEnum(int state,String stateInfo){
        this.state = state;
        this.staeInfo = stateInfo;
    }

    public static ProductCategoryEnum stateOf(int index){
        for (ProductCategoryEnum state:values()){
            if (state.getState() == index){
                return  state;
            }
        }
        return null;
    }

    public Integer getState() {
        return state;
    }

    public String getStaeInfo() {
        return staeInfo;
    }
}
