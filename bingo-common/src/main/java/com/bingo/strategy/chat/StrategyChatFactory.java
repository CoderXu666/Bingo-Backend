package com.bingo.strategy.chat;

import com.bingo.enums.ResponseEnum;
import com.bingo.exception.BingoException;
import org.apache.commons.lang3.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-08  17:43
 * @Description: 工厂模式
 * @Version: 1.0
 */
public class StrategyChatFactory {
    private static final Map<Integer, AbstractChatStrategy> STRATEGY_MAP = new HashMap<>();

    /**
     * 策略类注册到Map中
     */
    public static void register(Integer code, AbstractChatStrategy strategyHandler) {
        STRATEGY_MAP.put(code, strategyHandler);
    }

    /**
     * 获取策略处理器（根据策略标识）
     */
    public static AbstractChatStrategy getStrategyHandler(Integer code) {
        AbstractChatStrategy strategyHandler = STRATEGY_MAP.get(code);
        if (ObjectUtils.isEmpty(strategyHandler)) {
            throw new BingoException(ResponseEnum.NO_STRATEGY_HANDLER);
        }
        return strategyHandler;
    }
}
