package com.hytc.o2o.enums;

public enum HeadLineEnum {
    SUCCESS(0,"创建成功"),INSERT_ERROR(-1001,"创建失败"),EMPTY(-1002,"区域信息为空"),DELETE_ERROR(-1003,"删除失败");
    private int state;

    private String staeInfo;

    HeadLineEnum(int state, String staeInfo) {
        this.state = state;
        this.staeInfo = staeInfo;
    }
    public static HeadLineEnum getHeadLineEnum(int state){
        for (HeadLineEnum headLineEnum:values()){
            if(headLineEnum.getState() == state){
                return headLineEnum;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStaeInfo() {
        return staeInfo;
    }

    public void setStaeInfo(String staeInfo) {
        this.staeInfo = staeInfo;
    }
}
