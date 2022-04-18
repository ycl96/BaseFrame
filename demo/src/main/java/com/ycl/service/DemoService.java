package com.ycl.service;

import com.ycl.common.utils.MsgSendUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author : YangChunLong
 * @date : Created in 2020/7/31 17:26
 * @description:
 * @modified By:
 * @version: :
 */
public class DemoService {
    private static Logger logger = LoggerFactory.getLogger(DemoService.class);

    private static ServerSocketChannel serverSocketChannel;
//    多路复用选择器
    private static Selector selector;

    public static void main(String[] args) throws Exception{
        System.out.println("start server ...");
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
        //监听端口
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //切换非阻塞
        serverSocketChannel.configureBlocking(false);
        //绑定端口
        serverSocketChannel.bind(new InetSocketAddress("localhost",9696));
        //注册选择器
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (selector.select() > 0){
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while(iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                if(selectionKey.isAcceptable()){
                    //客户端已完成连接
                    MsgSendUtil.client = serverSocketChannel.accept();
                    //切换成非阻塞状态
                    MsgSendUtil.client.configureBlocking(false);
                    //注册到选择器上 监听读就绪事件
                    MsgSendUtil.client.register(selector, SelectionKey.OP_READ);
                }else if(selectionKey.isReadable()){
                    //有读事件,读取就绪的信息
                    MsgSendUtil.read(selectionKey);
                }
                //删除已处理事件
                iterator.remove();
            }
        }
    }

    private static void initServer() throws Exception{
//        创建服务端通道
        serverSocketChannel = ServerSocketChannel.open();
//        设置为非阻塞类型
        serverSocketChannel.configureBlocking(false);
//        绑定监听端口
        serverSocketChannel.socket().bind(new InetSocketAddress(8000));
//        创建多路复用选择器
        selector = Selector.open();
//        注册通道的监听事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

    }

    private static void startServer() throws IOException{

    }

    private static void reveiveMsg(SelectionKey key) throws IOException{
        SocketChannel socketChannel = (SocketChannel)key.channel();
//        分配缓冲区区域
        ByteBuffer buffer = ByteBuffer.allocate(2048);
//        将channel 中的数据读取到buffer中
        int i =socketChannel.read(buffer);
        if (i != -1){
//            从缓冲区中读取数据，并解析成字符串
            String msg = new String(buffer.array()).trim();
            System.out.println("服务器接收到消息："+msg);
//            调用write方法，向channel中写入数据
            socketChannel.write(ByteBuffer.wrap(("reply: "+msg).getBytes()));
        }else {
            socketChannel.close();
        }
    }

}
