package personal.nathan;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * Created by za-zhangwei002 on 19-11-10.
 */
public class ChatroomServer {

    public static void main(String[] args) {

    }

    public void start() {
        try {
            // 1.获取Selector选择器
            Selector selector = Selector.open();
            // 2.获取通道
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            ServerSocket serverSocket = serverSocketChannel.socket();
            InetSocketAddress address = new InetSocketAddress("127.0.0.1", 6000);
            // 3.设置为非阻塞
            serverSocketChannel.configureBlocking(false);
            // 4.绑定连接
            serverSocket.bind(address);
            // 5.将通道注册到选择器上（可接收）
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("serverChannel is listening...");

            // 6.选择相关IO事件
            while (selector.select() > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                // 轮询事件
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (null == key) {
                        continue;
                    }

                    // 3.设置为非阻塞
                    serverSocketChannel.configureBlocking(false);
                    // 4.将通道注册到选择器上(可读)
                    serverSocketChannel.register(selector, SelectionKey.OP_READ);

                }
            }

//            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT)
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
