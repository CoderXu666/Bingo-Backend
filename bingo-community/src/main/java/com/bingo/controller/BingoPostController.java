package com.bingo.controller;


import com.bingo.feign.CommunityUserFeign;
import com.bingo.resp.R;
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
        R userInfo = userFeign.findByUserId(10086L);
        System.out.println(userInfo);
    }
}

