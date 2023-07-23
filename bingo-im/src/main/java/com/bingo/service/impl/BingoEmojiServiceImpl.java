package com.bingo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoEmojiMapper;
import com.bingo.pojo.dto.EmojiDTO;
import com.bingo.pojo.po.BingoEmoji;
import com.bingo.service.BingoEmojiService;
import com.bingo.store.BingoEmojiStore;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-07-18
 */
@Service
public class BingoEmojiServiceImpl extends ServiceImpl<BingoEmojiMapper, BingoEmoji> implements BingoEmojiService {

    @Autowired
    BingoEmojiStore bingoEmojiStore;

    /**
     * 保存一張表情包
     */
    @Override
    public Boolean saveEmoji(EmojiDTO emojiDTO) {
        BingoEmoji bingoEmoji = new BingoEmoji();
        BeanUtils.copyProperties(emojiDTO, bingoEmoji);
        return bingoEmojiStore.saveEmoji(bingoEmoji);
    }

    /**
     * 刪除一張表情包
     */
    @Override
    public Boolean deleteEmojiById(Long id) {
        // 语法糖！
        // () -> System.out.println(1111);  实现类对象

        // 匿名实现类对象
        Runnable runnable = () -> System.out.println(1111);
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println(111);
            }
        };
        Thread t = new Thread(runnable1);
        new Thread(runnable).start();
        t.start();

        return bingoEmojiStore.deleteEmojiById(id);
    }

    /**
     * 表情包展示（按照最新时间排序）
     */
    @Override
    public List findEmojiByUserId(Long userId) {
        List<BingoEmoji> emojiList = bingoEmojiStore.findEmojiByUserId(userId);
        List<String> lists = emojiList.stream().map(item -> item.getEmojiUrl()).collect(Collectors.toList());
        return lists;
    }
}
