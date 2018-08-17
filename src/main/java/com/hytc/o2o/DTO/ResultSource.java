package com.hytc.o2o.DTO;

import java.io.Serializable;

public class ResultSource  <T> implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -604819219519449516L;

    private T data;

    private boolean success;

    private String  resultCode;

    private Integer errorCode;

    public ResultSource(){
        this.success=false;
    }

    /**
     * 成功时候的构造函数
     */
    public ResultSource(boolean success,T data){
        this.success = success;
        this.data=data;
    }
}
