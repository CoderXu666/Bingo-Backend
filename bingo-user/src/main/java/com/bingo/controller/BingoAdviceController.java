package com.bingo.controller;


import com.bingo.pojo.dto.BingoAdviceDTO;
import com.bingo.resp.R;
import com.bingo.service.BingoAdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 用户建议表 前端控制器
 * </p>
 *
 * @author 徐志斌
 * @since 2023-03-01
 */
@RestController
@RequestMapping("/advice")
public class BingoAdviceController {

    @Autowired
    private BingoAdviceService bingoAdviceService;

    /**
     * 保存建议和评价
     * @return
     */
    @PostMapping("save")
    public R saveAdvice(@RequestBody BingoAdviceDTO adviceDTO) {
        try {
            bingoAdviceService.saveAdvice(adviceDTO);
            return R.succeed("", "保存成功");
        } catch (Exception e) {
            return R.failed(e, "操作失败");
        }
    }
}

