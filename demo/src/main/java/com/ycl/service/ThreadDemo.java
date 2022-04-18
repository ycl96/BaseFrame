package com.ycl.service;

/**
 * @author : YangChunLong
 * @date : Created in 2021/9/26 16:53
 * @description:
 * @modified By:
 * @version: :
 */
public class ThreadDemo {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "线程开始运行");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.currentThread().sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setName("线程B");
        thread.start();
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("ssssssssss");
    }
}
