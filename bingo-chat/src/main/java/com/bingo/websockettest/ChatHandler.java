package com.bingo.websockettest;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

/**
 * @Author 徐志斌
 * @Date: 2023/5/28 16:28
 * @Version 1.0
 * @Description: 处理消息的Handler
 * <p>
 * TextWebSocketFrame：在netty中，用于为websocket专门处理文本的对象，frame是消息的载体
 * Channel是有生命周期函数的,WebSocket当然也有
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    /**
     * 记录管理所有客户端的channel
     */
    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     *
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        // 获取客户端发送的字符串
        String content = msg.text();
        System.out.println(content);

        // 发送给所有客户端（客户端通过channel获取数据）
        for (Channel channel : clients) {
            channel.writeAndFlush(new TextWebSocketFrame("[服务器接收到消息:] " + LocalDateTime.now() + ",消息为:" + content));
        }

        // 这个和上面for循环一个意思
//        clients.writeAndFlush(new TextWebSocketFrame("[服务器接收到消息:] " + LocalDateTime.now() + ",消息为:" + content));
    }

    /**
     * 当客户端连接到Netty Server（打开连接）
     * 获取客户端Channel，存入到ChannelGroup中进行管理
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        clients.add(ctx.channel());
    }

    /**
     * 客户端从Netty Server上移除
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        // 当触发handlerRemoved，ChannelGroup会自动移除对应客户端的channel，下面那行代码不用写，注释掉
//        clients.remove(ctx.channel());
        System.out.println("客户端断开,channel对应的长id为:" + ctx.channel().id().asLongText());
        System.out.println("客户端断开,channel对应的短id为:" + ctx.channel().id().asShortText());
    }
}
