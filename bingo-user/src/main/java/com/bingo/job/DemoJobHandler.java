package com.bingo.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

/**
 * @Author 徐志斌
 * @Date: 2023/5/20 15:50
 * @Version 1.0
 * @Description: TestJobHandler
 */
@Component
public class DemoJobHandler {

    @XxlJob("testJobHandler")
    public ReturnT<String> testJobHandler() {
        System.out.println("testJobHandler......");
        return ReturnT.SUCCESS;
    }

    public static void main(String[] args) {
    }
}
