package com.bingo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoChatListShowMapper;
import com.bingo.pojo.po.im.BingoChatListShow;
import com.bingo.pojo.vo.user.BingoUserVO;
import com.bingo.service.BingoChatListShowService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 聊天窗口列表（展示） 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-08-24
 */
@Service
public class BingoChatListShowServiceImpl extends ServiceImpl<BingoChatListShowMapper, BingoChatListShow> implements BingoChatListShowService {
    /**
     * 查询聊天列表
     */
    @Override
    public List<BingoUserVO> getChatList(String userId) {
        return null;
    }
}
