package com.bingo.controller;


import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.dto.GroupInfoDTO;
import com.bingo.pojo.resp.R;
import com.bingo.service.BingoGroupInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 徐志斌
 * @since 2023-07-13
 */
@RestController
@RequestMapping("/group_chat")
public class BingoGroupInfoController {
    @Autowired
    private BingoGroupInfoService groupChatService;

    /**
     * 创建群聊
     */
    @PostMapping("/save")
    public R saveGroup(@RequestBody GroupInfoDTO groupChatDTO) {
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
    public R updateGroup(@RequestBody GroupInfoDTO groupChatDTO) {
        groupChatService.updateGroup(groupChatDTO);
        return R.out(RespCodeEnum.SUCCESS, "更新成功");
    }

}

