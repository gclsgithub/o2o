package com.hytc.o2o.exceptions;

/**
 * @author hytc
 */
public class ShopRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 5022875738337242854L;

    public ShopRuntimeException(String errorMsg){
        super(errorMsg);
    }
}
