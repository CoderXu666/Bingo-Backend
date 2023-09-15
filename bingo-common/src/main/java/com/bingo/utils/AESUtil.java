package com.bingo.utils;


import com.bingo.exception.BingoException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;

/**
 * @Author 徐志斌
 * @Date: 2023/5/8 21:45
 * @Version 1.0
 * @Description: AESUtils
 */
public class AESUtil {
    private static final String SECRET_KEY_ALGORITHM = "AES";
    private static final String CIPHER_TRANSFORMATION = "AES/ECB/PKCS5Padding";
    private static final String SECRET_KEY = "bingoxuzhibin666";

    /**
     * 加密
     */
    public static String encrypt(String password) {
        try {
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec keySpec = new PBEKeySpec(SECRET_KEY.toCharArray(), SECRET_KEY.getBytes(), 65536, 256);
            SecretKey secret = new SecretKeySpec(secretKeyFactory.generateSecret(keySpec).getEncoded(), SECRET_KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secret);
            byte[] encryptedBytes = cipher.doFinal(password.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new BingoException(null);
        }
    }

    /**
     * 解密
     */
    public static String decrypt(String encryptedPassword) {
        try {
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec keySpec = new PBEKeySpec(SECRET_KEY.toCharArray(), SECRET_KEY.getBytes(), 65536, 256);
            SecretKey secret = new SecretKeySpec(secretKeyFactory.generateSecret(keySpec).getEncoded(), SECRET_KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secret);
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedPassword);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new BingoException(null);
        }
    }
}


