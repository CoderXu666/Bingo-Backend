package com.bingo.im.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author 徐志斌
 * @Date: 2023/5/28 19:36
 * @Version 1.0
 * @Description: Netty服务器(用于聊天)
 * ------------------------------------------------------------
 * 服务端流程：
 * 1.创建Netty服务端
 * 2.创建Netty Channel初始化Handler
 * 3.创建Netty Channel消息Handler
 */
@Slf4j
@Component
public class NettyServer {
    @Autowired
    private NettyServerChannelInitHandler initializerHandler;

    /**
     * boss接受客户端连接等事件，work处理boss接收的事件
     */
    private EventLoopGroup bossGroup;
    private EventLoopGroup workGroup;

    /**
     * 启动 Netty Server
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
     * Netty Chat Server启动
     */
    private void start() throws InterruptedException {
        bossGroup = new NioEventLoopGroup();
        workGroup = new NioEventLoopGroup();

        // 定义IM Server启动器
        try {
            ServerBootstrap server = new ServerBootstrap();
            server.group(bossGroup, workGroup);
            server.channel(NioServerSocketChannel.class);
            // 为每个连接成功的Channel绑定子处理器
            server.childHandler(initializerHandler);

            // Netty Server绑定端口号
            ChannelFuture channelFuture = server.bind(9099).sync();
            log.info("Server started and listen on:{}", channelFuture.channel().localAddress());

            // 关闭 Channel
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
