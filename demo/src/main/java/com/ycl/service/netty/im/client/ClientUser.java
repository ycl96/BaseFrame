package com.ycl.service.netty.im.client;

import com.ycl.service.netty.im.User;
import io.netty.buffer.ByteBuf;

import java.nio.ByteBuffer;

/**
 * @author : YangChunLong
 * @date : Created in 2021/9/30 17:09
 * @description:
 * @modified By:
 * @version: :
 */
public class ClientUser implements User {
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
