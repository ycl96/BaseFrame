package com.ycl.service;

import com.ycl.PersonActivity;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

/**
 * @author : YangChunLong
 * @date : Created in 2021/10/18 22:51
 * @description:
 * @modified By:
 * @version: :
 */
@Service
public class DemoService {
    @DubboReference(group = "test",interfaceClass = PersonActivity.class,version = "1.0",check = false)
    private PersonActivity personActivity;

    public String getSay(){
        return personActivity.say("啦啦啦啦");
    }
}
