package com.ycl.service;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author : YangChunLong
 * @date : Created in 2021/9/26 15:22
 * @description:
 * @modified By:
 * @version: :
 */
public class ReadWriteLockDemo {
    Object data;
    volatile boolean cacheValid;
    final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public Object getData (){
        try {
//            获取读锁，读的过程可以多线程共享读，但是写操作必须单个线程进行
            readWriteLock.readLock().lock();
//            如果缓存无效，则需要重置缓存，变更为写操作，因此需要独立进行，则锁升级，
//            需要现将读锁释放掉，再进行 写锁 获取，避免无用占用读锁产生资源浪费
            if (!cacheValid){
//                释放读锁
                readWriteLock.readLock().unlock();
//                获取写锁
                readWriteLock.writeLock().lock();
                try {
//                    双重校验是否有效，避免产生线程并发情况
                    if (!cacheValid){
//                        写入缓存操作逻辑执行
                        setData();
                        cacheValid = true;
                    }
//                    写入完成后，先不释放写锁，而是先行获取读锁，避免释放后其他"写锁"等待线程立即获取
//                    进行新一轮的写操作（会导致数据脏读现象，当前线程获取的数据和新一轮线程更新的数据不一致）
//                    由此，因为"读-写"互斥，先获取读锁后其他写锁等待线程无法获取
                    readWriteLock.readLock().lock();
                }finally {
//                    获取读锁后，再释放写锁
                    readWriteLock.writeLock().unlock();
                }
            }
            return data;
        } finally {
//            数据返回后，将读锁释放，操作完成
            readWriteLock.readLock().unlock();
        }

    }

    private Object setData (){
        int[] aaa = {1,2,4,5};
        data = aaa;
        return data;
    }
}
