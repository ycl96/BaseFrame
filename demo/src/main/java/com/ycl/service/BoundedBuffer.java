package com.ycl.service;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : YangChunLong
 * @date : Created in 2021/9/24 22:01
 * @description:
 * @modified By:
 * @version: :
 */
public class BoundedBuffer {
    final ReentrantLock lock = new ReentrantLock();
    final Condition putCondition = lock.newCondition();
    final Condition takeCondition = lock.newCondition();

    final int[] items = new int[2];
    int putptr, takeptr, count;

    public void put(int x){
        lock.lock();
        try {
            while (count == items.length){
                System.out.printf("----FULL---- The buffer is full!  %s has to wait.\n",
                        Thread.currentThread().getName());
                putCondition.await();
            }
            items[putptr] = x;
            if (++putptr == items.length){
                putptr = 0;
            }
            ++count;
            takeCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public int take() throws Exception{
        lock.lock();
        try {
            while (count == 0){
                System.out.printf("----EMPTY---- The buffer is empty!  %s has to wait.\n",
                        Thread.currentThread().getName());
                takeCondition.await();
            }
            int r = items[takeptr];
            if (++takeptr == items.length){
                takeptr = 0;
            }
            --count;
            putCondition.signal();
            return r;
        } finally {
            lock.unlock();
        }
    }
}
