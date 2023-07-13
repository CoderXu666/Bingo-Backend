package com.bingo.controller;


import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.dto.GroupChatDTO;
import com.bingo.pojo.resp.R;
import com.bingo.service.BingoGroupChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 周英俊
 * @since 2023-07-13
 */
@RestController
@RequestMapping("/group_chat")
public class BingoGroupChatController {
    @Autowired
    private BingoGroupChatService groupChatService;

    /**
     * 创建群聊
     */
    @PostMapping("/save")
    public R saveGroup(@RequestBody GroupChatDTO groupChatDTO) {
        groupChatService.saveGroup(groupChatDTO);
        return R.out(RespCodeEnum.SUCCESS, "创建成功");
    }

    /**
     * 删除群聊
     */
    @GetMapping("/delete")
    public R deleteGroup(Long id) {
        groupChatService.deleteGroup(id);
        return R.out(RespCodeEnum.SUCCESS, "删除成功");
    }

    /**
     * 更新群聊
     */
    @PostMapping("/update")
    public R updateGroup(@RequestBody GroupChatDTO groupChatDTO) {
        groupChatService.updateGroup(groupChatDTO);
        return R.out(RespCodeEnum.SUCCESS, "更新成功");
    }

}

