package com.github.nathan;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author nathan.z
 * @date 2020/11/15.
 */
public class NioServer {


    public static void startServer() throws IOException  {
        // 1、获取选择器
        Selector selector = Selector.open();
        // 2、获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 3、设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        // 4、绑定端口
        serverSocketChannel.bind(new InetSocketAddress(9000));
        // 5、将通道注册到选择器上，并注册到io事件为"接收新连接"
        /**
         * SelectionKey.OP_READ 传输通道可读
         * SelectionKey.OP_WRITE 传输通道可写
         * SelectionKey.OP_CONNECT 传输通道连接成功
         * SelectionKey.OP_ACCEPT 接收到新连接
         */
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 6、轮询感兴趣的事件
        while (selector.select() > 0) {
            Iterator<SelectionKey> selectionKeys = selector.selectedKeys().iterator();
            while (selectionKeys.hasNext()) {
                SelectionKey selectionKey = selectionKeys.next();
                if (selectionKey.isAcceptable()) {
                    System.out.println("接收到新连接！");
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    System.out.println("收到新数据！");
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    // 用allocate方法，初始化时为写模式
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int length = 0;
                    while ((length = socketChannel.read(byteBuffer)) > 0) {
                        // 翻转为读模式
                        byteBuffer.flip();
                        String data = new String(byteBuffer.array(), 0, length);
                        System.out.println(data);
                        // 复位byteBuffer
                        byteBuffer.clear();
                    }
                    socketChannel.close();
                }
                selectionKeys.remove();
//                else if (selectionKey.isWritable()) {
//                    System.out.println("可写！");
//                } else if (selectionKey.isConnectable()) {
//                    System.out.println("建立新连接！");
//                }
                //

            }
        }
        serverSocketChannel.close();
    }

    public static void main(String[] args) throws IOException {
        startServer();
    }
}
