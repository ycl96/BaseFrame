package com.ycl.service.lock;

/**
 * @author : YangChunLong
 * @date : Created in 2021/10/27 18:25
 * @description:
 * @modified By:
 * @version: :
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();
//        Thread thread = new Thread(new DemoThread(true,o1,o2));
//        Thread thread1 = new Thread(new DemoThread(false,o1,o2));
        final Demo demo = new Demo();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    demo.getName();
                }
            }
        });
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    demo.getAge();
                }
            }
        });
        thread.setName("demo1-thread");
        thread1.setName("demo2-thread");
        thread.start();
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        thread1.start();
    }

    public static class DemoThread implements Runnable{
        private boolean flag;
        private Object o1;
        private Object o2;
        DemoThread(boolean flag,Object o1, Object o2){
            this.flag = flag;
            this.o1 = o1;
            this.o2 = o2;
        }
        @Override
        public void run() {
            if (flag) {
                while (true) {
//                    demo.getName();
//                    demo.getAge();
                    synchronized (o1){
                        System.out.println("进入lock1，当前线程："+Thread.currentThread().getName());
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        synchronized (o2){
                            System.out.println("进入lock2，当前线程："+Thread.currentThread().getName());
                        }
                    }
                }
            }else {
                while (true){
//                    demo.getAge();
//                    demo.getName();
                    synchronized (o2){
                        System.out.println("进入lock2，当前线程："+Thread.currentThread().getName());
                        synchronized (o1){
                            System.out.println("进入lock1，当前线程："+Thread.currentThread().getName());
                        }
                    }
                }
            }
        }
    }


}


