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
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author : YangChunLong
 * @date : Created in 2021/10/15 01:52
 * @description:
 * @modified By:
 * @version: :
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.ycl")
@EnableScheduling
@Configuration
@EnableDubbo
public class AppStart {
    private static Logger logger = LoggerFactory.getLogger(AppStart.class);
    public static ConfigurableApplicationContext applicationContext = null;

    public static void main(String[] args) {
        logger.warn("开始启动服务，请等待...");
        applicationContext = SpringApplication.run(AppStart.class, args);
        System.out.println("服务启动成功！当前端口：" + ((AnnotationConfigServletWebServerApplicationContext) applicationContext).getWebServer().getPort());
    }
}
