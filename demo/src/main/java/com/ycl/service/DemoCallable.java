package com.ycl.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author : YangChunLong
 * @date : Created in 2021/9/24 15:58
 * @description:
 * @modified By:
 * @version: :
 */
public class DemoCallable {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final int j = i;
            Future f = executorService.submit(new Callable<Object>() {
                @Override
                public Object call() {
                    String s = "第"+j+"次";
                    return s;
                }
            });
            list.add(f);
        }
        for (Future future : list) {
            System.out.println(future.get().toString());
        }
    }
}
