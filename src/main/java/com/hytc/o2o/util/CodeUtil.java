package com.hytc.o2o.util;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CodeUtil {

    /**
     * 检查验证码是否正确
     *
     * @param request
     * @return
     */
    public static Boolean cheackVerfityCode(HttpServletRequest request) {

        String verifyCodeExpected = (String) request.getSession().getAttribute(
                com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);

        String inputCode = HttpRequestUtil.getString(request, "verifyCodeActual");

        if (verifyCodeExpected.equals(inputCode)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 生成二维码图片流
     *
     * @param content url
     * @param response
     */
    public static BitMatrix generateQrCodeStream(String content, HttpServletResponse response){
        response.setHeader("Cache-Control","no-store");
        response.setHeader("Pragma","no-cache");
        response.setDateHeader("Expires",0);
        response.setContentType("image/png");

        //设置图片的文字编码以及内边框框距
        Map <EncodeHintType,Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");
        hints.put(EncodeHintType.MARGIN,0);
        BitMatrix bitMatrix;

        try {
            //参数设置
            bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,300,300,hints);
        } catch (WriterException e) {
            e.printStackTrace();
            return  null;
        }
        return bitMatrix;
    }

}
