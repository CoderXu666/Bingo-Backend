package com.bingo.controller;


import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.po.BingoNoticeLog;
import com.bingo.resp.R;
import com.bingo.service.BingoNoticeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 徐志斌
 * @since 2023-06-04
 */
@RestController
@RequestMapping("/notice")
public class BingoNoticeLogController {
    @Autowired
    BingoNoticeLogService noticeLogService;

    /**
     * 根据userId,通知类型查询用用户的通知
     */
    @GetMapping("/find")
    public R find(String id, Integer type) {
        try {
            List<BingoNoticeLog> bingoNoticeLog = noticeLogService.find(id, type);
            return R.out(RespCodeEnum.SUCCESS, bingoNoticeLog);
        } catch (Exception e) {
            return R.out(RespCodeEnum.FAIL, "操作失败");
        }
    }
}

