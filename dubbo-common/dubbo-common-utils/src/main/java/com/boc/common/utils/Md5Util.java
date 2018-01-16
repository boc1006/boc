package com.boc.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类
 * 
 * @author DongQihong
 * 
 */
public class Md5Util {

    /**
     * 加密
     * 
     * @param str
     *            要加密的字符串
     * @return 加密后的32位密文,加密失败返回原字符串.
     */
    public static String encrypt(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            return str;
        } catch (UnsupportedEncodingException e) {
            return str;
        }
        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(
                        Integer.toHexString(0xFF & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }
        return md5StrBuff.toString();
    }

    /**
     * 验证明文是否与密码对应
     * 
     * @param plaintext
     *            字符串明文
     * @param ciphertext
     *            字符md5密文
     * @return 验证结果，<code>true</code>对应 <code>false</code>不对应
     */
    public static boolean validate(String plaintext, String ciphertext) {
        return ciphertext.equalsIgnoreCase(encrypt(plaintext));
    }

    /**
     * 验证密文是否与密码对应
     *
     * @param plaintext
     *            字符串密文
     * @param ciphertext
     *            字符md5密文
     * @return 验证结果，<code>true</code>对应 <code>false</code>不对应
     */
    public static boolean validateEquals(String plaintext, String ciphertext) {
        return ciphertext.equalsIgnoreCase(plaintext);
    }
}