package com.ycl.service.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author : YangChunLong
 * @date : Created in 2021/9/27 22:45
 * @description:
 * @modified By:
 * @version: :
 */
public class FruitUtil {
    public static void getFruitInfo(Class<?> clazz){
        String strFruitProvider = "供应商信息：\r\n";
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(FruitProvider.class)){
                field.setAccessible(true);
                FruitProvider fruitProvider = (FruitProvider) field.getAnnotation(FruitProvider.class);
                strFruitProvider = strFruitProvider + "供应商编号："+fruitProvider.id()+"\r\n供应商名称："+fruitProvider.name()
                        +"\r\n供应商地址："+fruitProvider.address();
                String methodName = "set"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1);
                Apple apple = new Apple();
                try {
                    Method method = clazz.getMethod(methodName,String.class);
                    try {
                        method.invoke(apple,strFruitProvider);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                System.out.println(apple.getAppleProvider());
            }
        }
    }

    public static void main(String[] args) {
        FruitUtil.getFruitInfo(Apple.class);
        Apple apple = new Apple();
        System.out.println(apple.getAppleProvider());
    }
}
