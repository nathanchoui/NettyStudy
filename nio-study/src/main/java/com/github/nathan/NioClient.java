package com.github.nathan;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author nathan.z
 * @date 2020/11/15.
 */
public class NioClient {

    public static void startClient() throws IOException {
        // 1、获取通道
        SocketChannel socketChannel = SocketChannel
                .open(new InetSocketAddress("127.0.0.1", 9000));
        // 2、配置为非阻塞
        socketChannel.configureBlocking(false);
        // 3、不断自旋直到连接成功
        while (socketChannel.finishConnect()) {

        }
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 默认为写
        byteBuffer.put("hello world".getBytes());
        // 翻转为读
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        socketChannel.shutdownOutput();
        socketChannel.close();
    }

    public static void main(String[] args) throws IOException {
        startClient();
    }
}
