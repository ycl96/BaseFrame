package com.ycl.service.netty.im.server;

import com.ycl.service.netty.im.User;

import java.nio.ByteBuffer;

/**
 * @author : YangChunLong
 * @date : Created in 2021/9/30 17:42
 * @description:
 * @modified By:
 * @version: :
 */
public class ServerUser implements User {
    @Override
    public void input() {
        while (true){
            String msg = scan.nextLine();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put(msg.getBytes());
            buffer.flip();
            msgList.add(buffer);
        }
    }
}
