package com.ycl.service.proxy;

/**
 * @author : YangChunLong
 * @date : Created in 2021/9/28 23:07
 * @description:
 * @modified By:
 * @version: :
 */
public class PersonImpl implements Person {
    @Override
    public String sayHello(String name) {
        return "hello !"+name;
    }

    @Override
    public String sayGoodBye(String name) {
        return "GoodBye ! "+name;
    }
}
