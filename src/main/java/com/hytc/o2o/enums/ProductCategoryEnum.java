package com.hytc.o2o.enums;

public enum ProductCategoryEnum {
    SUCCESS(0,"创建成功"),INSERT_ERROR(-1001,"创建失败"),EMPTY(-1002,"区域信息为空"),DELETE_ERROR(-1003,"删除失败");
    private int state;

    private String staeInfo;

    /**
     * 私有构造方法
     * @param state
     * @param stateInfo
     */
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
