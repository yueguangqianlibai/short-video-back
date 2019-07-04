package com.xtang.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: short-video-back
 * @Date: 2019/7/4 15:52
 * @Author: xTang
 * @Description:
 */
@RestController
@RequestMapping("test")
public class TestController {


    @RequestMapping("/test")
    public String testHello(){
        System.out.println("everything is ok!");
        return "project is ok";
    }

}
