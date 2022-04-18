package com.ycl.start;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author : YangChunLong
 * @date : Created in 2021/10/18 23:01
 * @description:
 * @modified By:
 * @version: :
 */
@SpringBootApplication
@EnableDubbo
@ComponentScan(value = "com.ycl")
@Configuration
public class StartUp {
    private static Logger logger = LoggerFactory.getLogger(StartUp.class);
    public static ConfigurableApplicationContext applicationContext = null;

    public static void main(String[] args) {
        logger.warn("开始启动服务，请等待...");
        applicationContext = SpringApplication.run(StartUp.class, args);
        System.out.println("服务启动成功！当前端口：" + ((AnnotationConfigServletWebServerApplicationContext) applicationContext).getWebServer().getPort());
    }
}
