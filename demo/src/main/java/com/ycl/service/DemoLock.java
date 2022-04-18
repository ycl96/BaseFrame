package com.ycl.service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : YangChunLong
 * @date : Created in 2021/9/24 19:30
 * @description:
 * @modified By:
 * @version: :
 */
public class DemoLock {
    private final ReentrantLock lock = new ReentrantLock(false);

    private void process(){
        lock.lock();
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("******  " + Thread.currentThread().getName() + " is printing " + i + "  ******");
    //            查询当前线程获取锁的次数
                int lockCount = lock.getHoldCount();
    //            查询等待此锁的线程数量
                int waitCount = lock.getQueueLength();
    //            查看当前锁类型，是否为"公平锁"
                boolean isFair = lock.isFair();
                System.out.printf("---holdCount: %d;\n---queuedLength:%d;\n---isFair: %s\n\n", lockCount, waitCount,
                        isFair);

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        final DemoLock demoLock = new DemoLock();
//        final CountDownLatch countDownLatch = new CountDownLatch(10);
//        long time1 = System.currentTimeMillis();
//        for (int i = 0; i < 10; i++) {
//            executorService.submit(new Runnable() {
//                @Override
//                public void run() {
//                    demoLock.process();
//                    countDownLatch.countDown();
//                }
//            });
//        }
//        try {
//            countDownLatch.await();
//            long time2 = System.currentTimeMillis();
//            System.out.println("--------------------------- 总耗时："+(time2-time1));
//            executorService.shutdown();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        BoundedBuffer boundedBuffer = new BoundedBuffer();
        ConditionThread put1 = new ConditionThread(boundedBuffer,"PUT1");
        ConditionThread put2 = new ConditionThread(boundedBuffer,"PUT2");
        ConditionThread put3 = new ConditionThread(boundedBuffer,"PUT3");

        ConditionThread take1 = new ConditionThread(boundedBuffer,"TAKE1");
        ConditionThread take2 = new ConditionThread(boundedBuffer,"TAKE2");

        put1.start();
        put2.start();
        put3.start();
        take1.start();
        take2.start();

    }


}
