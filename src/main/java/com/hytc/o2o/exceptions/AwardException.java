package com.hytc.o2o.exceptions;

public class AwardException extends RuntimeException {

    private static final long serialVersionUID = 2992902310770040215L;

    public AwardException(String errorMsg){
        super(errorMsg);
    }
}
