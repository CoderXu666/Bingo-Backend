package com.bingo;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.Environment;

import java.util.TimeZone;

/**
 * @Author: 徐志斌
 * @CreateDate: 2023/3/4
 * @description:
 * @Version: 1.0
 */
@Slf4j
@EnableFeignClients
@MapperScan("com.bingo.mapper")
@EnableDiscoveryClient
@SpringBootApplication
public class BingoCommunityApplication implements CommandLineRunner {
    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SpringApplication.run(BingoCommunityApplication.class);
    }

    @Override
    public void run(String... args) {
        String applicationName = environment.getProperty("spring.application.name");
        String serverPort = environment.getProperty("server.port");
        log.info("----------------------------【Bingo】社区服务名称：{}---------------", applicationName);
        log.info("----------------------------【Bingo】社区服务端口：{}-----------------------", serverPort);
        log.info("----------------------------【Bingo】社区服务启动成功......-----------------------");
    }
}