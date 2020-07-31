package com.ycl.appstart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author : YangChunLong
 * @date : Created in 2020/7/31 17:59
 * @description: 启动类
 * @modified By:
 * @version: :
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.ycl")
@EnableScheduling
@Configuration
@MapperScan(value = "com.ycl.dao.mapper")
public class AppStartUp {
    public static ConfigurableApplicationContext applicationContext = null;
    public static void main(String[] args) {
        applicationContext = SpringApplication.run(AppStartUp.class,args);
        System.out.println("服务启动成功！当前端口："+((AnnotationConfigServletWebServerApplicationContext) applicationContext).getWebServer().getPort());
    }
}
