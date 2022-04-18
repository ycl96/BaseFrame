package com.ycl.service;

import com.ycl.common.utils.MsgSendUtil;
import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author : YangChunLong
 * @date : Created in 2021/9/23 20:27
 * @description:
 * @modified By:
 * @version: :
 */
public class NioClient {
    private SocketChannel socketChannel;

    public static void main(String[] args) throws Exception{
        final MsgSendUtil msgSendUtil = new MsgSendUtil();
        //收集待发送消息
        new Thread(new Runnable() {
            public void run() {
                msgSendUtil.inp();
            }
        }).start();
        //发送消息
        new Thread(new Runnable() {
            public void run() {
                msgSendUtil.send();
            }
        }).start();
        //连接端口
        MsgSendUtil.client = SocketChannel.open(new InetSocketAddress("localhost",9696));
        //默认为阻塞,切换为非阻塞模式
        MsgSendUtil.client.configureBlocking(false);
        //发送信息给服务端
        String msg = "连接已经建立!";
        //创建数据缓存区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(msg.getBytes());
        byteBuffer.flip();
        MsgSendUtil.client.write(byteBuffer);
        Selector selector = Selector.open();
        MsgSendUtil.client.register(selector, SelectionKey.OP_READ);
        while (selector.select() > 0){
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while(iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                if(selectionKey.isReadable()){
                    //有读事件,读取就绪的信息
                    MsgSendUtil.read(selectionKey);
                }
                //删除已处理事件
                iterator.remove();
            }
        }
    }


    private void initClient() throws IOException{
//        创建客户端通道，并绑定服务器IP地址以及端口
        socketChannel = SocketChannel.open(new InetSocketAddress("localhost",8000));
    }

    private void sendMsg(String msg) throws IOException{
        byte[] bytes = msg.getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
//        向通道发送数据
        socketChannel.write(buffer);
        socketChannel.close();
    }

    private void receiveMsg() throws IOException{
        ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
        socketChannel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array()));
//        socketChannel.close();
    }
}
