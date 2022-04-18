package com.ycl.service.lock;

/**
 * @author : YangChunLong
 * @date : Created in 2021/10/27 18:29
 * @description:
 * @modified By:
 * @version: :
 */
public class Demo {
    static Object o1 = new Object();
    static Object o2 = new Object();

    public Object lock1 = new Object();
    public Object lock2 = new Object();

    public synchronized void getName(){
        System.out.println("姓名，线程："+Thread.currentThread().getName());
    }

    public void getAge(){
        System.out.println("年龄，线程："+Thread.currentThread().getName());
    }
}
