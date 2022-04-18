package com.ycl.service.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author : YangChunLong
 * @date : Created in 2021/9/28 23:15
 * @description:  动态代理的调用处理器，拦截器便是基于此类开发的
 * @modified By:
 * @version: :
 */
public class InvocationHandlerImpl implements InvocationHandler {
    private Object object;

    public InvocationHandlerImpl(Object object){
        this.object = object;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //在代理真实对象前我们可以添加一些自己的操作
        // 此时可以有一些业务逻辑的处理，比如权限校验之类的逻辑，类似与拦截器
        System.out.println("在调用之前，我要干点啥呢？");
        System.out.println("Method:" + method);

        //当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        Object returnValue = method.invoke(object,args);

        //在代理真实对象后我们也可以添加一些自己的操作
        // 处理完 上述的method.invoke()逻辑后（可以认为是具体的业务逻辑），可以加入后续的处理，比如过滤等
        System.out.println("在调用之后，我要干点啥呢？");

        return returnValue;
    }
}
