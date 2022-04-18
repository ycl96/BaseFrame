package com.ycl.service;

import com.ycl.CarActivity;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author : YangChunLong
 * @date : Created in 2021/10/19 00:52
 * @description:
 * @modified By:
 * @version: :
 */
@DubboService(interfaceClass = CarActivity.class,group = "test",version = "1.0")
@ComponentScan
public class CarImpl implements CarActivity {
    @Override
    public String run() {
        return "呜呜呜呜呜呜呜呜";
    }
}
