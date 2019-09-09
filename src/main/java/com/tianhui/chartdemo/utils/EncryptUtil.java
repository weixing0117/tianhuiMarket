package com.tianhui.chartdemo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {
    private SecretKey secretKey;

    private static final Logger log = LoggerFactory.getLogger(EncryptUtil.class);

    public EncryptUtil() throws IOException, NoSuchAlgorithmException {
    }

    private String encryptBySHA(String input) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        messageDigest.update(input.getBytes());
        byte[] bytes = messageDigest.digest();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            if (stringBuffer.length() == 0) {
                stringBuffer.append("0");
            }
            stringBuffer.append(Integer.toHexString(0xff & bytes[i]));
        }
        return stringBuffer.toString();
    }

/*    private SecretKey getSecretKey() throws NoSuchAlgorithmException {

        KeyGenerator keygen=KeyGenerator.getInstance("AES");

        keygen.init(256, new SecureRandom(encrypt_rule.getBytes()));

        SecretKey original_key = keygen.generateKey();

        byte [] raw = original_key.getEncoded();

        return new SecretKeySpec(raw, "AES");
    }*/

    public static String cipherEncrypt(String content) {
        String result = "";
        byte [] byte_AES= new byte[0];
        try {
            Cipher cipher=Cipher.getInstance("AES");

            cipher.init(Cipher.ENCRYPT_MODE, SingletonSecretKey.getInstance().getSecretKeySpec());

            byte [] byte_encode=content.getBytes("utf-8");

            byte_AES = cipher.doFinal(byte_encode);
        } catch (Exception e) {
            log.error("加密失败 :{}", e.getMessage());
            return content;
        }
        result =  new String(new BASE64Encoder().encode(byte_AES));
        return result;
    }

    public static String cipherDecrypt(String content){
        String result = "";
        try {
            Cipher cipher=Cipher.getInstance("AES");

            cipher.init(Cipher.DECRYPT_MODE, SingletonSecretKey.getInstance().getSecretKeySpec());

            byte [] byte_content= new BASE64Decoder().decodeBuffer(content);
            /*
             * 解密
             */
            byte [] byte_decode=cipher.doFinal(byte_content);

            result = new String(byte_decode,"utf-8");
        } catch (Exception e) {
            log.error("解密失败 :{}", e.getMessage());
            return content;
        }
        return result;
    }
}


