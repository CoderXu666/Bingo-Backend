package com.bingo.feign;

import com.bingo.response.FeignResponse;
import com.bingo.pojo.vo.user.UserResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author 徐志斌
 * @Date: 2023/5/13 10:48
 * @Version 1.0
 */
@Component
@FeignClient(name = "bingo-user", url = "localhost:10001/user")
public interface UserFeign {
    /**
     * 根据id查询用户信息
     */
    @GetMapping("/find_by_id")
    FeignResponse<UserResp> findByUserId(@RequestParam("id") Long id);

    /**
     * 根据ids批量查询用户信息
     */
    @PostMapping("/list_by_ids")
    FeignResponse<List<UserResp>> getUserByIds(@RequestBody List<Long> ids);

    /**
     * 修改用户在线状态（针对聊天功能）
     */
    @PostMapping("/update_online")
    Boolean updateOnlineStatus(@RequestParam("uid") Long uid, @RequestParam("status") Integer status);
}
