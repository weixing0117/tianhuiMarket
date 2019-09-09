package com.tianhui.chartdemo.utils;

import sun.misc.BASE64Decoder;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SingletonSecretKey {
    private volatile static SingletonSecretKey singletonSecretKey;

    private SecretKeySpec secretKeySpec;

    private String encrypt_rule;


    private SingletonSecretKey() throws NoSuchAlgorithmException, IOException {
        encrypt_rule = new String (new BASE64Decoder().decodeBuffer(getKey()));

        KeyGenerator keygen=KeyGenerator.getInstance("AES");

        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");

        secureRandom.setSeed(encrypt_rule.getBytes());

        keygen.init(256, secureRandom);

        SecretKey original_key=keygen.generateKey();

        byte [] raw=original_key.getEncoded();

        secretKeySpec =  new SecretKeySpec(raw, "AES");
    }

    private String getKey() throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();

        byte[] buff = new byte[100];

        int rc = 0;
        InputStream in = null;

        if(System.getProperty("os.name").contains("indow")){
            in = this.getClass().getClassLoader().getResourceAsStream("cipher/cipher_key");
        }else{
            File file = new File("/usr/local/java/cipher_key");
            in = new FileInputStream(file);
        }

        while ((rc = in.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
            rc += rc;
        }
        byte[] in_b = swapStream.toByteArray();

        return new String(in_b);
    }

    public static SingletonSecretKey getInstance() throws NoSuchAlgorithmException, IOException {
        if(null == singletonSecretKey){
            synchronized (SingletonSecretKey.class){
                if(null == singletonSecretKey){
                    singletonSecretKey = new SingletonSecretKey();
                }
            }
        }
        return singletonSecretKey;
    }
    public SecretKeySpec getSecretKeySpec(){
        return secretKeySpec;
    }
}
