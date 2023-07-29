package com.bingo.im.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author 徐志斌
 * @Date: 2023/7/29 8:11
 * @Version 1.0
 * @Description: Netty Channel初始化子Handler
 */
@Component
public class NettyServerChannelInitHandler extends ChannelInitializer<Channel> {
    @Autowired
    private NettyServerHandler serverHandler;

    /**
     * 初始化与Netty Server建立连接的Channel
     */
    @Override
    protected void initChannel(Channel channel) {
        ChannelPipeline pipeline = channel.pipeline();
        // WebSocket基于http协议
        pipeline.addLast(new HttpServerCodec());
        // 写大数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());
        // HTTP数据在传输过程中是分段的，HttpObjectAggregator可以将多个段聚合
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        // 自定义子处理器(用于处理服务端接收到的数据)
        pipeline.addLast(serverHandler);
    }
}
