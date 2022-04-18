package com.ycl.service.copy;

import org.apache.catalina.User;

/**
 * @author : YangChunLong
 * @date : Created in 2021/9/28 10:45
 * @description:
 * @modified By:
 * @version: :
 */
public class Student implements Cloneable{
    private int id;
    private String name;
    private int height;

    private Shoes shoes;

    public Student(){

    }
    public Student(int id, String name, int height, Shoes shoes){
        this.id = id;
        this.name = name;
        this.height = height;
        this.shoes = shoes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Shoes getShoes() {
        return shoes;
    }

    public void setShoes(Shoes shoes) {
        this.shoes = shoes;
    }


    @Override
    protected Object clone() {
//        实现浅拷贝
//        Object object = null;
//        try {
//            object = super.clone();
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }

//        实现深拷贝
        Student object = null;
        try {
            object = (Student)super.clone();
            object.shoes = (Shoes)this.shoes.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }

        return object;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", height=" + height +
                ", shoes=" + shoes +
                '}';
    }
}
