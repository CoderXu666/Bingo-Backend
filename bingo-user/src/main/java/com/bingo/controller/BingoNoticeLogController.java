package com.bingo.controller;


import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.po.BingoNoticeLog;
import com.bingo.resp.R;
import com.bingo.service.BingoNoticeLogService;
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
public class BingoNoticeLogController {
    @Autowired
    private BingoNoticeLogService noticeLogService;

    /**
     * 根据Id,通知类型查询用户的通知
     */
    @GetMapping("/find")
    public R find(Long id, Integer type) {
        try {
            List<BingoNoticeLog> bingoNoticeLog = noticeLogService.findByIdAndType(id, type);
            return R.out(RespCodeEnum.SUCCESS, bingoNoticeLog);
        } catch (Exception e) {
            return R.out(RespCodeEnum.FAIL, "操作失败");
        }
    }

    /**
     * 保存用户通知
     */
    @PostMapping("/save")
    public R saveNotice(@RequestBody BingoNoticeLog noticeLog) {
        try {
            noticeLogService.saveNotice(noticeLog);
            return R.out(RespCodeEnum.SUCCESS, "操作成功");
        } catch (Exception e) {
            return R.out(RespCodeEnum.FAIL, "操作失败");
        }
    }

}

