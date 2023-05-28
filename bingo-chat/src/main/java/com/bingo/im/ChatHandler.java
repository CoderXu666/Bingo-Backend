package com.bingo.im;

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
 * TextWebSocketFrame类型， 表示一个文本帧
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    /**
     * 读取客户端的数据
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        log.info("服务器收到消息：{}", msg.text());
    }

    /**
     * 客户端连接到 Server 调用
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端连接到Netty服务器:" + ctx.channel().id().asLongText());
        NettyChannelConfig.getChannelGroup().add(ctx.channel());
    }

    /**
     * 客户端与 Server 断开连接时调用
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端与Netty服务器断开连接:" + ctx.channel().id().asLongText());
    }
}
