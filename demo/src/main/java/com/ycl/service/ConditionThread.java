package com.ycl.service;

/**
 * @author : YangChunLong
 * @date : Created in 2021/9/24 22:02
 * @description:
 * @modified By:
 * @version: :
 */
public class ConditionThread extends Thread {
    BoundedBuffer buffer = new BoundedBuffer();
    private String name;
    public ConditionThread(BoundedBuffer boundedBuffer, String name){
        super(name);
        this.name = name;
        this.buffer = boundedBuffer;
    }

    @Override
    public void run() {
        super.run();
        System.out.println(Thread.currentThread().getName() + " is running!");
        if (name.startsWith("PUT")){
            for (int i = 1; i < 4; i++) {
                buffer.put(i);
                System.out.printf("--PUT-- %s has put %d into the buffer.\n", Thread.currentThread().getName(), i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }else if (name.startsWith("TAKE")){
            for (int i = 1; i < 4; i++) {

                try {
                    int value = buffer.take();
                    System.out.printf("--TAK-- %s has took %d from the buffer.\n", Thread.currentThread().getName(),
                            value);
                    Thread.sleep(400);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
