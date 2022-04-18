package com.ycl.service.sync;

/**
 * @author : YangChunLong
 * @date : Created in 2021/10/26 13:16
 * @description:
 * @modified By:
 * @version: :
 */
public class DemoSynchronize {

    private synchronized void hello(){
        System.out.println("hello synchronize!");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void hi(){
        System.out.println("hi !!!!");
    }

    public static void main(String[] args) {
        final DemoSynchronize demoSynchronize = new DemoSynchronize();
        new Thread(new Runnable() {
            @Override
            public void run() {
                demoSynchronize.hello();
            }
        }).start();
        final DemoSynchronize demoSynchronize1 = new DemoSynchronize();
        new Thread(new Runnable() {
            @Override
            public void run() {
                demoSynchronize1.hello();
            }
        }).start();
    }
}
