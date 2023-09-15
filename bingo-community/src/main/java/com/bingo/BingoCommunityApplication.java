package com.bingo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.TimeZone;

/**
 * @Author: 徐志斌
 * @CreateDate: 2023/3/4
 * @description:
 * @Version: 1.0
 */
@EnableFeignClients
@MapperScan("com.bingo.mapper")
@EnableDiscoveryClient
@SpringBootApplication
public class BingoCommunityApplication {
    public static void main(String[] args) {
        // JVM 和 Linux 服务器时间保持一致
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SpringApplication.run(BingoCommunityApplication.class);
    }
}