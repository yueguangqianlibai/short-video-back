package com.xtang.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: short-video-back
 * @Date: 2019/7/4 15:52
 * @Author: xTang
 * @Description:
 */
@RestController
@Api(value = "测试服务的接口",tags = {"测试的响应接口(Controller)"})
@RequestMapping("test")
public class TestController {


    @ApiOperation(value = "测试接口",notes = "测试的接口notes")
    @RequestMapping("/test")
    public String testHello(){
        System.out.println("everything is ok!");
        return "project is ok i love my home";
    }

}
