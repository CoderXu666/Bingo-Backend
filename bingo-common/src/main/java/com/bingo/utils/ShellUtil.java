package com.bingo.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-01  10:13
 * @Description: Shell脚本执行工具类
 * @Version: 1.0
 */
@Slf4j
public class ShellUtil {
    /**
     * 执行Shell脚本
     */
    public static String runShell(String... args) {
        try {
            log.info("==============================开始执行Shell：{}===============================", args[1]);
            // 构造Shell命令
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command(args);
            processBuilder.redirectErrorStream(true);

            // 执行Shell
            Process process = processBuilder.start();

            // 读取Shell命令输出内容
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            String result = null;
            while ((line = reader.readLine()) != null) {
                result = line;
            }

            // 同步方法：等待Shell执行结束
            int exitCode = process.waitFor();

            // 执行成功，输出返回值
            if (exitCode == 0) {
                log.info("============================Shell执行成功：{}，命令：{}============================", args[1], Arrays.asList(args));
            } else {
                log.error("===========================Shell执行失败，文件名称为：{}，命令：{}===========================", args[1], Arrays.asList(args));
            }
            return result;
        } catch (Exception e) {
            log.error("===============================Shell执行异常，文件名称为：{}，命令：{}，异常信息：{}===============================", args[1], Arrays.asList(args), e);
            return null;
        }
    }
}
