package com.ycl.service.innerClass;

/**
 * @author : YangChunLong
 * @date : Created in 2021/9/28 00:28
 * @description:
 * @modified By:
 * @version: :
 */
public class InnerClass {
    private static int age;
    private String name;
    private String address;

    public static int getAge() {
        return age;
    }

    public static void setAge(int age) {
        InnerClass.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static class StaticInnerClass {
        private String number;

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public int getAge(){
            return age;
        }

        public String getName (){
            InnerClass innerClass = new InnerClass();
            return innerClass.getName();
        }
    }

    public class MemberInnerClass {
        private int height;

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getAge(){
            return age;
        }

        public String getName(){
            return InnerClass.this.getName();
        }

        public void test(Person person){
            System.out.println(person.getName());
        }
    }

    public String getParent (){
        class Parent{
            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
        return getName();
    }

    public abstract class Person{
        private String name;

        private boolean canSpeak;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isCanSpeak() {
            return canSpeak;
        }

        public void setCanSpeak(boolean canSpeak) {
            this.canSpeak = canSpeak;
        }

        public abstract int run();
    }

    public void test(Person person){
        System.out.println(person.getName());
    }

    public static void main(String[] args) {
        InnerClass.MemberInnerClass memberInnerClass = new InnerClass().new MemberInnerClass();
        memberInnerClass.test(new InnerClass().new Person(){
            @Override
            public int run() {
                return 0;
            }
//            public String getName(){
//                return "ok";
//            }
        });
    }
}
