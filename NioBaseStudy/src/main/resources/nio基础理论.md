### NIO由以下三个部分组成
* Channels:
 channel是一个双向通道（可读可写），区分与传统的stream。
 主要实现有：
  * FileChannel
  * DatagramChannel
  * SocketChannel
  * ServerSocketChannel
* Buffers
 
* Selectors