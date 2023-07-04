package com.bingo.feign;

import com.bingo.pojo.resp.FeignResponse;
import com.bingo.pojo.vo.BingoUserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author 周英俊
 * @Date: 2023/5/13 10:48
 * @Version 1.0
 * @Description: community 远程调用 user 模块
 */
@Component
@FeignClient(name = "bingo-user", url = "localhost:10000/api/user")
public interface TopicUserFeign {

    /**
     * 根据id查询用户信息
     */
    @GetMapping("/find_by_id")
    FeignResponse<BingoUserVO> findByUserId(@RequestParam("id") Long id);
}
