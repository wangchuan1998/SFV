package com.sfv.common;

import java.security.MessageDigest;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class MD5 {
	private static BASE64Encoder encoder = new BASE64Encoder();
    private static BASE64Decoder decoder = new BASE64Decoder();

    public static String Encode26(int numb) {
        String rslt = "";
        while (numb > 0) {
            rslt = ((char)(numb % 26 + 65)) + rslt;
            numb = numb / 26;
        }
        return rslt;
    }

    public static String Encode36(int numb) {
        String rslt = "";
        while (numb > 0) {
            int yu = numb % 36;
            if (yu < 26)
                rslt = ((char)(yu + 65)) + rslt;
            else
                rslt = Integer.toString(yu - 26) + rslt;
            numb = numb / 36;
        }
        return rslt;
    }

    /**
     * 采用MD5加密算法，不可逆
     * @param message 待加密的字符串
     * @return:加密结果
     * @throws java.lang.Exception
     */
    public static String MD5Encode(String message) {
        StringBuffer hexValue = new StringBuffer();
        try {
            MessageDigest messageDigest = 
                MessageDigest.getInstance("MD5"); //采用MD5加密算法
            char[] charArray = message.toCharArray();

            byte[] byteArray = new byte[charArray.length];

            for (int i = 0; i < charArray.length; i++)
                byteArray[i] = (byte)charArray[i];

            byte[] md5Bytes = messageDigest.digest(byteArray);


            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int)md5Bytes[i]) & 0xff;
                if (val < 16)
                    hexValue.append("0");
                hexValue.append(Integer.toHexString(val));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hexValue.toString();
    }

    /**
     * 采用BASE64加密算法进行加密，可逆
     * @param message 待加密的字符串
     * @return:加密结果
     */
    public static String BASE64Encode(String message) {
        return encoder.encode(message.getBytes());
    }

    /**
     * 采用BASE64加密算法进行解密
     * @param message 待解密字符串
     * @return:解密结果字符串
     */
    public static String BASE64Decode(String message) {
        try {
            if (message != null) {
                byte[] temp = decoder.decodeBuffer(message);
                return new String(temp);
            }
        } catch (java.io.IOException ioe) {
            ioe.printStackTrace();
        }
        return "";
    }

    public static String encryptStr(String srcStr) {
        String factor = "MessageEncrypt";
        return BASE64Encode(xorString(srcStr, factor));
    }

    public static String decryptStr(String srcStr) {
        String factor = "MessageEncrypt";
        return xorString(BASE64Decode(srcStr), factor);
    }

    public static String encryptStr(String srcStr, String factor) {
        if (factor == null || factor.equals(""))
            factor = "MessageEncrypt";
        return BASE64Encode(xorString(srcStr, factor));
    }

    public static String decryptStr(String srcStr, String factor) {
        if (factor == null || factor.equals(""))
            factor = "MessageEncrypt";
        return xorString(BASE64Decode(srcStr), factor);
    }

    /**
     * 将源字符串与指定的字符进行运算
     * @param sstr 源
     * @param xorstr 指定的因子
     * @return:结果字符串
     */
    public static String xorString(String sstr, String xorstr) {
        String rslt = "";

        char ch;
        char ch0;
        int l = sstr.length();
        int xl = xorstr.length();
        int j;
        for (int i = 0; i < l; i++) {
            ch = sstr.charAt(i);
            j = i % xl;
            ch0 = xorstr.charAt(j);
            ch ^= ch0;
            rslt = rslt + ch;
        }
        return rslt;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("suchy".indexOf("s"));
        System.out.println(BASE64Encode("suchy7035"));
        System.out.println(decryptStr("PhAQGxhQVXZb"));
        System.out.println(MD5Encode("fhkj"));
        System.out.println(MD5Encode("sss"));
        System.out.println("2008-02-01".compareTo("2008-02-01"));

        String s = "<a>xxx</a>";
        s = s.substring(s.indexOf(">") + 1);
        System.out.println(s);
        s = s.substring(0, s.indexOf("<"));
        System.out.println(s);
    }
}
