package com.bingo.websockettest;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @Author 徐志斌
 * @Date: 2023/5/28 16:00
 * @Version 1.0
 * @Description: WSServerInitializer
 */
public class WSServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new HttpServerCodec());  // websocket基于http协议，所有要有http编解码器
        pipeline.addLast(new ChunkedWriteHandler()); // 对写大数据流的支持
        // HTTP聚合器，对httpMessage进行聚合，聚合成FullHttpRequest或FullHttpResponse
        // 几乎Netty编程，都会使用到HttpObjectAggregator
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));

        /*---------------------------------以上是对HTTP支持------------------------------*/
        // websocket服务器处理的协议，用于指定给客户端链接访问的路由：/ws
        // 该handler会处理繁重、复杂的事，会处理握手动作handshaking
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        // 自定义Handler
        pipeline.addLast(new ChatHandler());
    }
}
