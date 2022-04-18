package com.ycl.service.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : YangChunLong
 * @date : Created in 2021/9/28 23:17
 * @description:
 * @modified By:
 * @version: :
 */
public class Test {
    public static void main(String[] args) throws Exception {
//        Person person = new PersonImpl();
//        InvocationHandler handler = new InvocationHandlerImpl(person);
//        ClassLoader classLoader = person.getClass().getClassLoader();
//        Class[] interfaces = person.getClass().getInterfaces();

        Class clazz = Class.forName("com.ycl.service.proxy.PersonImpl");
        ClassLoader classLoader = clazz.getClassLoader();
        Class[] interfaces = clazz.getInterfaces();
        Object object = clazz.newInstance();
        Method method = clazz.getMethod("sayHello", String.class);
        Object result = method.invoke(object,"测试反射嗷嗷嗷嗷");
        System.out.println(result.toString());
        InvocationHandler handler = new InvocationHandlerImpl(object);
        List<String> list = new ArrayList<>();
        List<String> list1 = new LinkedList<>();

        Person proxyPerson = (Person) Proxy.newProxyInstance(classLoader, interfaces, handler);
        System.out.println("动态代理类型：" + proxyPerson.getClass().getName());
        String hello = proxyPerson.sayHello("测试1号");
        String goodBye = proxyPerson.sayGoodBye("测试2号");
        System.out.println(hello);
        System.out.println(goodBye);

//        ExecutorService executorService = Executors.newFixedThreadPool()

    }

}
