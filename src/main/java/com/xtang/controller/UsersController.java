package com.xtang.controller;

import com.xtang.common.ServerResponse;
import com.xtang.pojo.Users;
import com.xtang.service.IUsersService;
import com.xtang.utils.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: short-video-back
 * @Date: 2019/7/4 23:00
 * @Author: xTang
 * @Description:
 */
@RestController
@RequestMapping("users")
public class UsersController {

    @Autowired
    private IUsersService iUsersService;

    @RequestMapping("regist")
    public ServerResponse regist(Users users) throws Exception{

        if (StringUtils.isBlank(users.getUsername()) || StringUtils.isBlank(users.getPassword())){
            return ServerResponse.createByErrorMsg("账号和密码为空");
        }
        if (iUsersService.queryUserNameIsExist(users.getUsername())){
            users.setNickname(users.getUsername());
            users.setPassword(MD5Utils.getMD5Str(users.getPassword()));
            users.setFansCounts(0);
            users.setReceiveLikeCounts(0);
            users.setFollowCounts(0);
            return iUsersService.saveUser(users);
        }else{
            return ServerResponse.createByErrorMsg("该账号已被注册");
        }
    }

    @RequestMapping("test")
    public String test(){
        return "test ok!";
    }


}
