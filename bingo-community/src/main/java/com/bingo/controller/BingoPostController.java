package com.bingo.controller;


import com.bingo.feign.CommunityUserFeign;
import com.bingo.pojo.vo.BingoUserVO;
import com.bingo.resp.FeignResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 徐志斌
 * @since 2023-05-13
 */
@RestController
@RequestMapping("/post")
public class BingoPostController {
    @Autowired
    private CommunityUserFeign userFeign;

    @GetMapping("/test")
    public void test() {
        FeignResult<BingoUserVO> userInfo = userFeign.findByUserId(10086L);
        BingoUserVO userVO = userInfo.getData();
        System.out.println(userVO);
    }
}

