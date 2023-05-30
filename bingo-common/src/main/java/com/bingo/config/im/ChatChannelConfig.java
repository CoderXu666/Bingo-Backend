package com.bingo.config.im;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author 徐志斌
 * @Date: 2023/5/28 19:29
 * @Description: 管理 Channel 信息(文字聊天)
 */
public class ChatChannelConfig {
    /**
     * 1:作用:定义一个Channel组,管理所有的channel
     * GlobalEventExecutor.INSTANCE:单例
     * <p>
     * 2:UserId 与 Channel 对应关系(用于给指定用户发送消息)
     */
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static ConcurrentHashMap<String, Channel> userChannelMap = new ConcurrentHashMap<>();

    /**
     * 获取channel组
     */
    public static ChannelGroup getChannelGroup() {
        return channelGroup;
    }

    /**
     * 获取用户channel map
     */
    public static ConcurrentHashMap<String, Channel> getUserChannelMap() {
        return userChannelMap;
    }
}
