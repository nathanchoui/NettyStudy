package com.github.nathan;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author nathan.z
 * @date 2020/12/6.
 */
@Slf4j
public class NettyDiscardServer {

    private final int port;

    public NettyDiscardServer(int port) {
        this.port = port;
    }

    ServerBootstrap serverBootstrap = new ServerBootstrap();

    public void start() {
        // boss线程组
        EventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup();
        try {
            serverBootstrap.group(bossLoopGroup, workerLoopGroup);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.localAddress(port);
            serverBootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            serverBootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    //FIXME
                    socketChannel.pipeline().addLast(null);
                }
            });
        } catch (Exception e) {
            log.error("start server failed", e);
            e.printStackTrace();
        }
    }
}
