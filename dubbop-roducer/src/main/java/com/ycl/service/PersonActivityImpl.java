package com.ycl.service;

import com.ycl.PersonActivity;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.util.List;

/**
 * @author : YangChunLong
 * @date : Created in 2021/10/18 20:46
 * @description:
 * @modified By:
 * @version: :
 */
@DubboService(interfaceClass = PersonActivity.class,group = "test",version = "1.0")
@ComponentScan
public class PersonActivityImpl implements PersonActivity {
    @Override
    public String say(String lan) {
        return "我可以讲话，比如："+lan;
    }

    @Override
    public void walk() {
        System.out.println("我走路了 哈哈哈哈");
        try {
            ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181",10000,new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    System.out.println(event.getType() + " -- " + event.getPath()); // 事件的类型  --   事件发生的节点
                }
            });
            List<String> data = zooKeeper.getChildren("/test_node",true,new Stat());
            System.out.println(data);
            zooKeeper.create("/test_node/lock/lock","123".getBytes(),null, CreateMode.EPHEMERAL_SEQUENTIAL);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        PersonActivityImpl activity = new PersonActivityImpl();
        activity.walk();
    }

    @Override
    public void sleep() {
        System.out.println("我睡觉了 哈哈哈哈");
    }

    @Override
    public String eat(String food) {
        return "我在吃东西："+food;
    }
}
