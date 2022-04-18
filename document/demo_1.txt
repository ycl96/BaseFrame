package com.ycl.common.utils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author : YangChunLong
 * @date : Created in 2021/9/24 10:27
 * @description:
 * @modified By:
 * @version: :
 */
public class MsgSendUtil {
    /**
     * 控制台输入收集
     */
    private static Scanner scan = new Scanner(System.in);
    /**
     * 待发送消息列表
     */
    private static List<ByteBuffer> bufferList = Collections.synchronizedList(new ArrayList<ByteBuffer>(4));
    /**
     * 连接
     */
    public static SocketChannel client;
    private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    /**
     * 控制台输入
     */
    public void inp(){
        while (true){
            String msg = scan.nextLine();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put(msg.getBytes());
            buffer.flip();
            bufferList.add(buffer);
        }
    }

    /**
     * 数据发送
     */
    public void send() {
        while (true){
            if(bufferList.size() > 0){
                ByteBuffer buffer = bufferList.get(0);
                try {
                    if(client != null){
                        client.write(buffer);
                        bufferList.remove(0);
                    }else{
                        Thread.sleep(3000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 读取已完成的读就绪时间传送的信息
     * @param selectionKey 就绪读事件对应选择键
     * @throws IOException e
     */
    public static void read(SelectionKey selectionKey) throws IOException {
        SocketChannel client = (SocketChannel) selectionKey.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (client.read(buffer) > 0){
            buffer.flip();
            byte[] bytes = new byte[buffer.limit()];
            buffer.get(bytes);
            System.out.print(sdf.format(new Date()) + " 接收消息:" + new String(bytes));
        }
        System.out.println();
    }
}
