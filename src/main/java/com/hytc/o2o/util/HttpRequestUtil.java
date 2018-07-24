package com.hytc.o2o.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 负责处理HttpServletRequest 参数
 */
public class HttpRequestUtil {

    public static Integer getInt(HttpServletRequest request,String key){
        try {
            return Integer.decode(request.getParameter(key));
        }catch (Exception e){
            return -1;
        }
    }
    public static Long getLong(HttpServletRequest request,String key){
        try {
            return Long.valueOf(request.getParameter(key));
        }catch (Exception e){
            return -1L;
        }
    }

    public static Double getDouble(HttpServletRequest request,String key){
        try {
            return Double.valueOf(request.getParameter(key));
        }catch (Exception e){
            return -1d;
        }

    }
    public static Boolean getBoolean(HttpServletRequest request,String key){
        try {
            return Boolean.valueOf(request.getParameter(key));
        }catch (Exception e){
            return false;
        }
    }
    public static String getString(HttpServletRequest request,String key){
        try {
            String requestParam = request.getParameter(key);
            if (null != requestParam) {
                requestParam = requestParam.trim();
            }
            if ("".equals(requestParam)) {
                requestParam = null;
            }
            return requestParam;
        }catch (Exception ex){
            return  null;
        }
    }

}
