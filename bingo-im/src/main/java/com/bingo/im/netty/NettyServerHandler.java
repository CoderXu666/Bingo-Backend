package com.bingo.im.netty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bingo.config.ChatChannelConfig;
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
 * @Description: Netty服务器事件、消息处理器
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class NettyServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    /**
     * 作用：读取客户端的数据
     * <p>
     * 注意：用户发送数据时候，调用了Controller，并没有在js中使用socket.send api
     * 这里还有种实现方式就是发送消息不调用Controller，而是js调用socket.send
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) {
        JSONObject msgJson = JSON.parseObject(msg.text());
        Long userId = msgJson.getLong("userId");
        ChatChannelConfig.getUserChannelMap().put(userId, ctx.channel());
        AttributeKey<Long> key = AttributeKey.valueOf("userId");
        // 相当于为channel做个标识，用于removeUserId()
        ctx.channel().attr(key).setIfAbsent(userId);
    }

    /**
     * Client 与 Server 连接
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        ChatChannelConfig.getChannelGroup().add(ctx.channel());
    }

    /**
     * Client 与 Server 断开
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        ChatChannelConfig.getChannelGroup().remove(ctx.channel());
        removeUserId(ctx);
    }

    /**
     * 删除 ConcurrentHashMap 对应的用户信息
     */
    private void removeUserId(ChannelHandlerContext ctx) {
        AttributeKey<Long> key = AttributeKey.valueOf("userId");
        Long userId = ctx.channel().attr(key).get();
        ChatChannelConfig.getUserChannelMap().remove(userId);
    }
}
