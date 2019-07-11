package com.xtang.controller;

import com.xtang.common.ServerResponse;
import com.xtang.service.IBgmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: short-video-back
 * @Date: 2019/7/9 22:31
 * @Author: xTang
 * @Description:
 */

@RestController
@Api(value = "背景音乐的接口", tags = {"操作背景音乐的响应接口(Controller)"})
@RequestMapping("bgm")
public class BgmController {

    @Autowired
    private IBgmService iBgmService;

    @ApiOperation(value = "查询所有BGM接口", notes = "查询所有BGM的接口服务")
    @PostMapping("list")
    public ServerResponse list(){
        return ServerResponse.createBySuccessAll("读取成功",iBgmService.queryBgmList());
    }

}
