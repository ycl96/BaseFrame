package com.ycl.service.annotation;

import java.lang.annotation.*;

/**
 * @author : YangChunLong
 * @date : Created in 2021/9/27 22:40
 * @description:
 * @modified By:
 * @version: :
 */
@Target(ElementType.FIELD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
//@Inherited
public @interface FruitProvider {
    public int id() default -1;
    public String name() default "";
    public String address() default "";
}
