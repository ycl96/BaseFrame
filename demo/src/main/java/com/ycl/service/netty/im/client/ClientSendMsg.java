package com.ycl.service.netty.im.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author : YangChunLong
 * @date : Created in 2021/9/30 17:43
 * @description:
 * @modified By:
 * @version: :
 */
public class ClientSendMsg {
    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientChannelInit());
            bootstrap.connect("127.0.0.1",8000);
        }finally {

        }
    }
}
