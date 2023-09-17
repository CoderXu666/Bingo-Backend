package com.bingo.strategy.uploadfile;

import com.bingo.enums.ResponseEnum;
import com.bingo.exception.BingoException;
import com.bingo.strategy.action.AbstractTargetStrategy;
import org.apache.commons.lang3.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 徐志斌
 * @Date: 2023/9/17 18:21
 * @Version 1.0
 * @Description: 工厂模式
 */
public class StrategyUploadFactory {
    private static final Map<Integer, AbstractUploadStrategy> STRATEGY_MAP = new HashMap<>();

    /**
     * 策略类注册到Map中
     */
    public static void register(Integer code, AbstractUploadStrategy strategyHandler) {
        STRATEGY_MAP.put(code, strategyHandler);
    }

    /**
     * 获取策略处理器（根据策略标识）
     */
    public static AbstractUploadStrategy getStrategyHandler(Integer code) {
        AbstractUploadStrategy strategyHandler = STRATEGY_MAP.get(code);
        if (ObjectUtils.isEmpty(strategyHandler)) {
            throw new BingoException(ResponseEnum.NO_STRATEGY_HANDLER);
        }
        return strategyHandler;
    }
}
