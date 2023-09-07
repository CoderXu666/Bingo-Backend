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
     * 作用：读取客户端的数据
     * 逻辑：首次建立链接，前端会调用send发送uid。后端在这里绑定一下Channel和uid关系
     * socket.onopen = () => {
     * socket.send(this.userId)
     * }
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame frame) {
        JSONObject msg = JSON.parseObject(frame.text());
        Long userId = msg.getLong("userId");
        NettyChannelRelation.getUserChannelMap().put(userId, ctx.channel());
        AttributeKey<Long> key = AttributeKey.valueOf("userId");
        // 相当于为channel做个标识，用于removeUserId()
        ctx.channel().attr(key).setIfAbsent(userId);
    }

    /**
     * Client 与 Server 连接
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        NettyChannelRelation.getChannelGroup().add(ctx.channel());
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
                log.info("30s未检测到心跳，断开链接");
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
