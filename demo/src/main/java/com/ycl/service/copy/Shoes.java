package com.ycl.service.copy;

/**
 * @author : YangChunLong
 * @date : Created in 2021/9/28 10:47
 * @description:
 * @modified By:
 * @version: :
 */
public class Shoes implements Cloneable{
    private int length;
    private String brand;
    private String color;

    public Shoes(){

    }

    public Shoes(int length, String brand, String color){
        this.length = length;
        this.brand = brand;
        this.color = color;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Shoes{" +
                "length=" + length +
                ", brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    @Override
    protected Object clone() {
        Object object = null;
        try{
            object = super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return object;
    }
}
