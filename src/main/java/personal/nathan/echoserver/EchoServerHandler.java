package personal.nathan.echoserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * Description:
 * <p>
 * Created by nathan.z on 2018/6/2.
 */
// 标示一个channelHandler可以被多个channel安全地共享
@ChannelHandler.Sharable
public class EchoServerHandler implements ChannelInboundHandler {
    public void channelRegistered(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    public void channelUnregistered(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    // 每个消息读入都会调用该方法
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        ByteBuf byteBuf = (ByteBuf) o;
        System.out.println("Server recieved: " + byteBuf.toString(CharsetUtil.UTF_8));
        channelHandlerContext.write(byteBuf);
    }

    // 在最后一次调用channelRead之后会调用。
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
        channelHandlerContext.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }

    public void channelWritabilityChanged(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    // 在读取操作期间，有异常抛出时会调用。
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {
        throwable.printStackTrace();
        channelHandlerContext.close();
    }

    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {

    }
}
