package com.bingo.im;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bingo.config.im.ChatChannelConfig;
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
     * <p>
     * 同步用户 和 Channel的对应关系 (K:userId V:channel)
     * <p>
     * 注意:这里不会接收用户聊天内容,用户发送数据时候,调用了Controller,并没有在js中使用socket.send api
     * 所以换个角度说,这里还有种实现方式就是发送消息不调用Controller,而是js调用socket.send
     * 该方法接收后在做处理,也是可以实现的
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) {
        JSONObject jsonObject = JSON.parseObject(msg.text());
        String userId = jsonObject.getString("userId");
        ChatChannelConfig.getUserChannelMap().put(userId, ctx.channel());
        AttributeKey<String> key = AttributeKey.valueOf("userId");
        ctx.channel().attr(key).setIfAbsent(userId);
    }

    /**
     * 客户端连接到 Server 调用
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        ChatChannelConfig.getChannelGroup().add(ctx.channel());
    }

    /**
     * 客户端与 Server 断开连接时调用
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        ChatChannelConfig.getChannelGroup().remove(ctx.channel());
        removeUserId(ctx); // 移除userId 和 Channel关系数据
    }

    // 删除用户与channel的对应关系
    private void removeUserId(ChannelHandlerContext ctx) {
        AttributeKey<String> key = AttributeKey.valueOf("userId");
        String userId = ctx.channel().attr(key).get();
        ChatChannelConfig.getUserChannelMap().remove(userId);
    }
}
