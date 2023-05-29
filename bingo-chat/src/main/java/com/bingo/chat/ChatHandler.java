package com.bingo.chat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bingo.config.NettyChannelConfig;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author 徐志斌
 * @Date: 2023/5/28 20:10
 * @Version 1.0
 * @Description: ChatHandler
 * <p>
 * TextWebSocketFrame类型,表示一个文本帧
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    /**
     * 读取客户端的数据
     * 同步用户 和 Channel的对应关系 (K:userId V:channel)
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) {
        JSONObject jsonObject = JSON.parseObject(msg.text());
        String userId = jsonObject.getString("userId");
        NettyChannelConfig.getUserChannelMap().put(userId, ctx.channel());
        AttributeKey<String> key = AttributeKey.valueOf("userId");
        ctx.channel().attr(key).setIfAbsent(userId);
    }

    /**
     * 客户端连接到 Server 调用
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        log.info("客户端连接到Netty服务器:" + ctx.channel().id().asLongText());
        NettyChannelConfig.getChannelGroup().add(ctx.channel());
    }

    /**
     * 客户端与 Server 断开连接时调用
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        log.info("客户端与Netty服务器断开连接:" + ctx.channel().id().asLongText());
        NettyChannelConfig.getChannelGroup().remove(ctx.channel());
        removeUserId(ctx); // 移除userId 和 Channel关系数据
    }

    // 删除用户与channel的对应关系
    private void removeUserId(ChannelHandlerContext ctx) {
        AttributeKey<String> key = AttributeKey.valueOf("userId");
        String userId = ctx.channel().attr(key).get();
        NettyChannelConfig.getUserChannelMap().remove(userId);
    }
}
