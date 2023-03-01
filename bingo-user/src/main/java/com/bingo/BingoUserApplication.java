package com.bingo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: 徐志斌
 * @CreateDate: 2023/3/1
 * @description:
 * @Version: 1.0
 */
@ComponentScan("com.bingo.*")
@SpringBootApplication
public class BingoUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(BingoUserApplication.class);
    }
}