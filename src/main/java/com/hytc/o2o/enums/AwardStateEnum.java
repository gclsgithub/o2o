package com.hytc.o2o.enums;

public enum AwardStateEnum {
    NULL_SHOPID("-1","奖品不存在");
    private String code ;

    private String msgl;

    AwardStateEnum(String code, String msgl) {
        this.code = code;
        this.msgl = msgl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsgl() {
        return msgl;
    }

    public void setMsgl(String msgl) {
        this.msgl = msgl;
    }
}
