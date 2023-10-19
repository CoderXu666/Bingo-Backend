package com.bingo.utils;


import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Author 徐志斌
 * @Date: 2023/5/8 21:45
 * @Version 1.0
 * @Description: AESUtils
 */
@Slf4j
public class AESUtil {
    private static final String KEY = "Bingo_XZB_666666"; // AES加密Key为16位

    /**
     * 加密
     */
    public static String encrypt(String sSrc) {
        try {
            if (KEY.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = KEY.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); // "算法/模式/补码方式"
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
            return new org.apache.tomcat.util.codec.binary.Base64().encodeToString(encrypted); // 此处使用BASE64做转码功能，同时能起到2次加密的作用。
        } catch (Exception e) {
            log.error("===============AES加密失败：{}===============", e.getMessage());
            return null;
        }
    }

    /**
     * 解密
     */
    public static String decrypt(String sSrc) {
        try {
            if (KEY == null && KEY.length() != 16) {
                log.error("===============AES加密KEY格式不正确：{}===============", KEY);
                return null;
            }
            byte[] raw = KEY.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = new Base64().decode(sSrc);
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original, "utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception e) {
            log.error("AES解密失败：{}", e.getMessage());
            return null;
        }
    }
}


