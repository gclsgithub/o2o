package com.hytc.o2o.util;



import javax.servlet.http.HttpServletRequest;

public class CodeUtil {
    /**
     *
     * @param request
     * @return
     */
    public static  Boolean cheackVerfityCode(HttpServletRequest request){

        String verifyCodeExpected = (String) request.getSession().getAttribute(
                com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);

        String inputCode = HttpRequestUtil.getString(request,"verifyCodeActual");

        if (verifyCodeExpected.equals(inputCode)){
            return true;
        }else{
            return false;
        }
    }
}
