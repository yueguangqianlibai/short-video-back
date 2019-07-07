package com.xtang.controller;

import com.xtang.utils.RedisOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: short-video-back
 * @Date: 2019/7/4 15:52
 * @Author: xTang
 * @Description:
 */
@RestController
@Api(value = "redis服务的接口",tags = {"redis的响应接口(BasicController)"})
@RequestMapping("redis")
public class BasicController {

    @Autowired
    public RedisOperator operator;

    public static final String USERS_REDIS_SESSION = "users-redis-session";

}
