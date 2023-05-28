package com.bingo.im;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author 徐志斌
 * @Date: 2023/5/28 19:36
 * @Version 1.0
 * @Description: IMServer
 */
@Slf4j
@Component
public class IMServer {
    @Autowired
    private ChatHandler chatHandler;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workGroup;

    /**
     * 启动 IM Server(通过新线程启动Netty Server)
     */
    @PostConstruct
    private void init() {
        new Thread(() -> {
            try {
                start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * 服务器停止释放资源
     */
    @PreDestroy
    private void destroy() throws InterruptedException {
        if (bossGroup != null) {
            bossGroup.shutdownGracefully().sync();
        }
        if (workGroup != null) {
            workGroup.shutdownGracefully().sync();
        }
    }

    /**
     * Netty Server启动方法
     */
    private void start() throws InterruptedException {
        // 定义两个EventLoopGroup
        // boss负责接受客户端连接等事件,work负责处理boss接收到的事件
        bossGroup = new NioEventLoopGroup();
        workGroup = new NioEventLoopGroup();

        // 定义IM Server启动器
        try {
            ServerBootstrap server = new ServerBootstrap();
            server.group(bossGroup, workGroup);
            server.channel(NioServerSocketChannel.class);
            // 为每个连接成功的Channel绑定子处理器
            server.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel channel) throws Exception {
                    ChannelPipeline pipeline = channel.pipeline();
                    pipeline.addLast(new HttpServerCodec());     // WebSocket基于http协议
                    pipeline.addLast(new ChunkedWriteHandler()); // 写大数据流的支持
                    // HTTP数据在传输过程中是分段的，HttpObjectAggregator可以将多个段聚合
                    pipeline.addLast(new HttpObjectAggregator(1024 * 64));
                    pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
                    pipeline.addLast(chatHandler); // 自定义子处理器(用于处理服务端接收到的数据)
                }
            });

            // Netty Server绑定端口号
            ChannelFuture channelFuture = server.bind(9099).sync();
            log.info("Server started and listen on:{}", channelFuture.channel().localAddress());

            // 监听关闭Channel
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
