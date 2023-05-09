package com.bingo.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

import static com.xxl.job.core.biz.model.ReturnT.SUCCESS;

@Component
public class DemoJobHandler {
    @XxlJob("demoJobHandler")
    public ReturnT<String> execute() {
        System.out.println("XXL-JOB测试");
        return SUCCESS;
    }
}
