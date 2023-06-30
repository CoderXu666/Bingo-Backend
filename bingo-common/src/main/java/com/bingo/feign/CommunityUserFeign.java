package com.bingo.feign;

import com.bingo.pojo.vo.BingoUserVO;
import com.bingo.pojo.resp.FeignResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author 徐志斌
 * @Date: 2023/5/13 10:48
 * @Version 1.0
 * @Description: community 远程调用 user 模块
 */
@Component
@FeignClient(name = "bingo-user", url = "localhost:10000/api/user")
public interface CommunityUserFeign {
    /**
     * 根据id查询用户信息
     */
    @GetMapping("/find_by_id")
    FeignResponse<BingoUserVO> findByUserId(@RequestParam("id") Long id);

    /**
     * 根据ids批量查询用户信息
     */
    @PostMapping("/get_list")
    FeignResponse<List<BingoUserVO>> getUserInfoByIds(@RequestParam("ids") List<Long> ids);
}
