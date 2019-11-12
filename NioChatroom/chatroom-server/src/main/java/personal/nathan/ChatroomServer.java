package personal.nathan;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
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
            // 5.将通道注册到选择器上（接收新连接）
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("serverChannel is listening...");

            // 6、轮询感兴趣的I/O就绪事件（选择键集合）
//            while (selector.select() > 0) {
//                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
//                // 轮询事件
//                while (iterator.hasNext()) {
//                    SelectionKey selectedKey = iterator.next();
//
//                    if (selectedKey.isAcceptable()) {
//                        // 10、若选择键的IO事件是“连接就绪”事件,就获取客户端连接
//                        SocketChannel socketChannel = serverSocketChannel.accept();
//                        // 11、切换为非阻塞模式
//                        socketChannel.configureBlocking(false);
//                        // 12、将该通道注册到selector选择器上
//                        socketChannel.register(selector, SelectionKey.OP_READ);
//                    } else if (selectedKey.isReadable()) {
//                        // 13、若选择键的IO事件是“可读”事件,读取数据
//                        SocketChannel socketChannel = (SocketChannel) selectedKey.channel();
//                        // 14、读取数据
//                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
//                        int length = 0;
//                        while ((length = socketChannel.read(byteBuffer)) > 0) {
//                            byteBuffer.flip();
//                            Logger.info(new String(byteBuffer.array(), 0, length));
//                            byteBuffer.clear();
//                        }
//                        socketChannel.close();
//                    }
//
//                    // 15、移除选择键
//                    selectedKeys.remove();
//                }
//            }
// 7、关闭连接
            serverSocketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
