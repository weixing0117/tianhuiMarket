package com.tianhui.chartdemo;

import com.tianhui.chartdemo.utils.EncryptUtil;
import sun.misc.BASE64Decoder;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Test {
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
      /*  StringBuffer stringBuffer = new StringBuffer("1,2,3,4,5,6,");
        System.out.println(stringBuffer.substring(1,stringBuffer.length()-1));
        System.out.println(EncryptUtil.cipherEncrypt("DUCKG"));
        System.out.println(EncryptUtil.cipherEncrypt("616743096297758720"));
        System.out.println(EncryptUtil.cipherDecrypt("NePIawUV7CH5tUcPdLsTtg=="));
        System.out.println("24_Je_d--E6q6KWRrvFbORC-QhjX29pBfT2EtRRnVb0i72dA4y62c6YPYafeBcKfiZ8i-_-2u1BxOb19nh5BJrduA".length());
        System.out.println((Calendar.getInstance().getTimeInMillis()+"").length());
        System.out.println(UIdGenerator.getInstance().getUId());*/

                System.out.println(EncryptUtil.cipherDecrypt("V67ChM6iGLevUo59wcTTSQ=="));
        System.out.println(EncryptUtil.cipherDecrypt("NePIawUV7CH5tUcPdLsTtg=="));
        System.out.println(new String (new BASE64Decoder().decodeBuffer("dGlhbmh1aU1hcmtldOWKoOWvhuaWh+S7tnRpYW5odWlNYXJrZXTliqDlr4bmlofku7Y=")));

    }
}
