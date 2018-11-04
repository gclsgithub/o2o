package com.hytc.o2o.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * DES堆成算法（对称算法）
 */
public class DESUtil {

    private static Key key;

    //设置秘钥Key
    private static final String KEY_STR =  "hytcKey";

    //设置编码方式
    private static final String CHARSET = "UTF-8";

    private static final String ALGORITHM = "DES";

    static {
        try {
            //生成DES算法对象
            KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);

            //运用SHAI安全策略
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");

            //设置秘钥
            secureRandom.setSeed(KEY_STR.getBytes());

            //初始化基于SHAI的算法对象
            generator.init(secureRandom);

            //生成秘钥
            key = generator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取加密字符串
     * @param str
     * @return
     */
    public static String getEncocodingString(String str){

        BASE64Encoder base64Encoder = new BASE64Encoder();

        try {
            byte[] bytess = str.getBytes();

            Cipher cipher = Cipher.getInstance(ALGORITHM);

            cipher.init(Cipher.ENCRYPT_MODE,key);

            byte[] doFinal = cipher.doFinal(bytess);

            return base64Encoder.encode(doFinal);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * 获取解码
     */
    public static String getDecryptString(String str){

        //基于Base64编码，接受byte[] 并转换成String
        BASE64Decoder base64Decoder = new BASE64Decoder();

        byte[] doFinal = new byte[0];
        try {
            byte[] bytes = base64Decoder.decodeBuffer(str);

            Cipher cipher = Cipher.getInstance(ALGORITHM);

            cipher.init(Cipher.DECRYPT_MODE,key);

            doFinal = cipher.doFinal(bytes);

            return new String(doFinal,CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    //TEST
    public static void main(String[] args){
        System.out.println(getEncocodingString("123456"));
        System.out.println(getDecryptString("ifC7Qe9mBP8="));
    }
}
