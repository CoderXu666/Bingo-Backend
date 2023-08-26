package com.bingo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @Author 徐志斌
 * @Date: 2023/8/26 13:23
 * @Version 1.0
 * @Description: RechargeFeign
 */
@Component
@FeignClient(name = "bingo-recharge", url = "localhost:10000/api/user")
public interface RechargeFeign {
}
