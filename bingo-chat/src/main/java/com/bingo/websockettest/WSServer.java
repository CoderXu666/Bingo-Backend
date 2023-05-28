package com.bingo.websockettest;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Author 徐志斌
 * @Date: 2023/5/28 15:55
 * @Version 1.0
 * @Description: WSServer
 */
public class WSServer {
    public static void main(String[] args) throws Exception {
        // 两个线程组
        EventLoopGroup mainGroup = new NioEventLoopGroup();
        EventLoopGroup subGroup = new NioEventLoopGroup();

        try {
            // 创建Netty服务器
            ServerBootstrap server = new ServerBootstrap();
            server.group(mainGroup, subGroup);
            server.channel(NioServerSocketChannel.class);
            server.childHandler(new WSServerInitializer());

            // 绑定端口
            ChannelFuture future = server.bind(8088).sync();

            // 监听关闭channel
            future.channel().closeFuture().sync();
        } finally {
            mainGroup.shutdownGracefully();
            subGroup.shutdownGracefully();
        }
    }
}
