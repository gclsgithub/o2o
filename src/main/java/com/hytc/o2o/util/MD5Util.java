package com.hytc.o2o.util;

import org.springframework.util.DigestUtils;

public class MD5Util {

    public static String getEncode(String attrArgs){
        return  DigestUtils.md5DigestAsHex(attrArgs.getBytes());
    }
}
