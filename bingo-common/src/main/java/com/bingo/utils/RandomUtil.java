package com.bingo.utils;

import java.util.Random;

/**
 * @Author 徐志斌
 * @Date: 2023/8/10 20:51
 * @Version 1.0
 * @Description: 随机数生成器
 */
public class RandomUtil {
    /**
     * 生成随机数
     */
    public static String generateCode(int size) {
        String code = "";
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            int type = r.nextInt(3);
            switch (type) {
                // 大写字符（A 65-Z 65+25）
                case 0:
                    char ch = (char) (r.nextInt(26) + 65);
                    code += ch;
                    break;
                // 小写字符（a 97-z 97+25）
                case 1:
                    char ch1 = (char) (r.nextInt(26) + 97);
                    code += ch1;
                    break;
                // 数字字符
                case 2:
                    code += r.nextInt(10);
                    break;
            }
        }
        return code;
    }

    public static void main(String[] args) {
        String code = generateCode(10);//5位验证码
        System.out.println("随机验证码" + code);
    }
}
