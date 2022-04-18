package com.ycl.controller;

import com.ycl.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : YangChunLong
 * @date : Created in 2021/10/18 22:53
 * @description:
 * @modified By:
 * @version: :
 */
@Controller
@RequestMapping(value = "/hello")
public class DemoController {
    @Autowired
    private DemoService demoService;

    @ResponseBody
    @RequestMapping(value = "/demo")
    public String helloDemo(){
        return demoService.getSay();
    }
}
