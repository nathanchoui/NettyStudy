package com.github.nathan;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author nathan.z
 * @date 2021/1/17.
 */
public class MyInboundHandlerTests {

    @Test
    public void testInboundLifeCircle() {
        final MyInboundHandler inboundHandler = new MyInboundHandler();
        ChannelInitializer channelInitializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel embeddedChannel) throws Exception {
                embeddedChannel.pipeline().addLast(inboundHandler);
            }
        };
        EmbeddedChannel channel = new EmbeddedChannel();
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeInt(1);
        channel.writeInbound(byteBuf);
        channel.flush();
        channel.close();

        try {
            TimeUnit.MILLISECONDS.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }




}

