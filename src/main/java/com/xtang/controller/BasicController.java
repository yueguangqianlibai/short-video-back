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

    /**
     *     文件保存的空间
     */

    public static final String FILE_SPACE = "G:/short-video-back-dev";
    /**
     *      ffmpeg所在目录
     */
    public static final String FFMPEG_EXE = "G:\\ffmpeg\\bin\\ffmpeg.exe";

    /**
     * 每页分页的数量
     */
    public static final Integer PAGE_SIZE = 5;

}
