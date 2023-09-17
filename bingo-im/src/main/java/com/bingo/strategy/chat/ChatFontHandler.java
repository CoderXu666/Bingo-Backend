package com.bingo.strategy.chat;

import com.bingo.config.ThreadPoolConfig;
import com.bingo.enums.ChatRecordEnum;
import com.bingo.pojo.po.im.BingoChatSendRecord;
import com.bingo.pojo.po.im.BingoChatShow;
import com.bingo.store.BingoChatShowStore;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-08  17:38
 * @Description: 文字策略Handler
 * @Version: 1.0
 */
@Slf4j
@Component
public class ChatFontHandler extends AbstractChatStrategy {
    @Autowired
    @Qualifier(ThreadPoolConfig.CHAT_POOL_NAME)
    private ThreadPoolTaskExecutor taskExecutor;
    @Autowired
    private BingoChatShowStore showStore;

    /**
     * 当前策略枚举
     */
    @Override
    ChatRecordEnum getEnum() {
        return ChatRecordEnum.FONT;
    }

    /**
     * 处理会话窗口，更新未读数量
     *
     * @param sendRecord
     * @return
     */
    @Override
    public Boolean handleChatRecord(BingoChatSendRecord sendRecord) {
        Long uid = sendRecord.getUid();
        Long goalId = sendRecord.getGoalId();

        // 处理聊天会话窗口
        BingoChatShow uRecord = showStore.getOneRecord(uid, goalId);
        BingoChatShow goalRecord = showStore.getOneRecord(goalId, uid);
        if (ObjectUtils.isEmpty(uRecord)) {
            showStore.saveRecord(uid, goalId);
        } else if (ObjectUtils.isEmpty(goalRecord)) {
            showStore.saveRecord(goalId, uid);
        }

        // 更新未读数量
        uRecord.setUnreadCount(uRecord.getUnreadCount() + 1);
        uRecord.setReceiveTime(new Date());
        return showStore.updateRecordById(uRecord);
    }
}
