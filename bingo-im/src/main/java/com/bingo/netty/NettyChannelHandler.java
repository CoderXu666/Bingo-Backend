package com.bingo.netty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author 徐志斌
 * @Date: 2023/5/28 20:10
 * @Version 1.0
 * @Description: WebSocket Channel处理器
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class NettyChannelHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    /**
     * 作用：读取客户端的数据（包括客户端心跳也会接收）
     * 目的：首次建立连接，存储Channel和Uid关系
     * -------------------------------------------------------------
     * 1.客户端发送消息调用Controller，没有在js使用socket.send
     * 2.客户端发送消息不调用Controller，而是js使用用socket.send通过Channel传递数据
     * 这里WebSocket只想做推送，不做接受，所以采用第一种方式算了
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame frame) {
        JSONObject msg = JSON.parseObject(frame.text());
        Long userId = msg.getLong("userId");
        NettyChannelRelation.getUserChannelMap().put(userId, ctx.channel());
        NettyChannelRelation.getChannelGroup().add(ctx.channel());
        AttributeKey<Long> key = AttributeKey.valueOf("userId");
        // 相当于为channel做个标识，用于removeUserId()
        ctx.channel().attr(key).setIfAbsent(userId);
    }

    /**
     * Client 与 Server 连接
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
    }

    /**
     * Client 与 Server 断开
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        NettyChannelRelation.getChannelGroup().remove(ctx.channel());
        removeUserId(ctx);
    }

    /**
     * 连接握手、心跳事件
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        // WebSocket建立连接事件调用
        if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
            System.out.println("首次建立连接，握手完成.....");
        }

        // WebSocket心跳断开事件
        else if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                // TODO 用户下线
                ctx.channel().close();
            }
        }
    }

    /**
     * 删除 ConcurrentHashMap 对应的用户信息
     */
    private void removeUserId(ChannelHandlerContext ctx) {
        AttributeKey<Long> key = AttributeKey.valueOf("userId");
        Long userId = ctx.channel().attr(key).get();
        NettyChannelRelation.getUserChannelMap().remove(userId);
    }
}
