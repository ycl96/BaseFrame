package com.ycl.service.annotation;

/**
 * @author : YangChunLong
 * @date : Created in 2021/9/27 22:44
 * @description:
 * @modified By:
 * @version: :
 */
public class Apple {
    @FruitProvider(id = 123,address = "河北省",name = "工业集团")
    private String appleProvider;

    private String appleType;

    public String getAppleProvider() {
        return appleProvider;
    }

    public void setAppleProvider(String appleProvider) {
        this.appleProvider = appleProvider;
    }

    public String getAppleType() {
        return appleType;
    }

    public void setAppleType(String appleType) {
        this.appleType = appleType;
    }
}
