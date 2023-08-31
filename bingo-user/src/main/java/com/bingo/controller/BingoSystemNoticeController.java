package com.bingo.controller;


import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.po.BingoSystemNotice;
import com.bingo.pojo.common.response.R;
import com.bingo.service.BingoSystemNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
public class BingoSystemNoticeController {
    @Autowired
    private BingoSystemNoticeService noticeLogService;

    /**
     * 根据Id,通知类型查询用户的通知
     */
    @GetMapping("/find")
    public R find(Long id, Integer type) {
        List<BingoSystemNotice> BingoSystemNotice = noticeLogService.findByIdAndType(id, type);
        return R.out(RespCodeEnum.SUCCESS, BingoSystemNotice);
    }

    /**
     * 保存用户通知
     */
    @PostMapping("/save")
    public R saveNotice(@RequestBody BingoSystemNotice noticeLog) {
        noticeLogService.saveNotice(noticeLog);
        return R.out(RespCodeEnum.SUCCESS, "操作成功");
    }
}

