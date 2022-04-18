package com.ycl.service.copy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : YangChunLong
 * @date : Created in 2021/9/28 10:20
 * @description:
 * @modified By:
 * @version: :
 */
public class CopyDemo {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        List<Integer> list1 = list;
//        直接赋值
        list1.remove(3);
        System.out.println(list1);
        System.out.println(list);

//        浅拷贝：基本数据类型采用值传递，引用数据类型采用引用传递形式的拷贝
//        结果：新对象修改基本数据类型数据不会影响原始对象，新对象修改引用类型数据会影响到原始对象
//        Shoes shoes = new Shoes(280,"nike","red");
//        Student student = new Student(1,"学生1",190,shoes);
//        Student student1 = (Student)student.clone();
//        student1.setHeight(170);
//        student1.setName("学生2");
//        student1.getShoes().setBrand("adidas");
//        System.out.println("student:"+student);
//        System.out.println("student1:"+student1);

//        深拷贝：基本数据类型和引用数据类型的数据 全部采用值传递
//        结果：新对象无论是修改基本类型数据还是引用类型数据，均不会影响到原始对象
//        实现方法上：对象重写的clone()方法逻辑有所不同
        Shoes shoes = new Shoes(280,"nike","red");
        Student student = new Student(1,"学生1",190,shoes);
        Student student1 = (Student)student.clone();
        student1.setHeight(170);
        student1.setName("学生2");
        student1.getShoes().setBrand("adidas");
        System.out.println("student:"+student);
        System.out.println("student1:"+student1);


    }
}
