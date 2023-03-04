package com.bingo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: 徐志斌
 * @CreateDate: 2023/3/4
 * @description:
 * @Version: 1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class BingoCommunityApplication {
    public static void main(String[] args) {
        SpringApplication.run(BingoCommunityApplication.class);
    }
}