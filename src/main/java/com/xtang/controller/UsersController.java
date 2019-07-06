package com.xtang.controller;

import com.xtang.common.ServerResponse;
import com.xtang.pojo.Users;
import com.xtang.service.IUsersService;
import com.xtang.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @program: short-video-back
 * @Date: 2019/7/4 23:00
 * @Author: xTang
 * @Description:
 */
@RestController
@Api(value = "用户相关服务的接口",tags = {"注册和登录的响应接口(Controller)"})
@RequestMapping("users")
public class UsersController {

    @Autowired
    private IUsersService iUsersService;

    @ApiOperation(value = "用户注册接口",notes = "用户注册的接口notes")
    @PostMapping("regist")
    public ServerResponse regist(Users users) throws Exception{
        if (StringUtils.isBlank(users.getUsername()) || StringUtils.isBlank(users.getPassword())){
            return ServerResponse.createByErrorMsg("账号和密码为空");
        }
        if (iUsersService.queryUserNameIsExist(users.getUsername())){
            if (StringUtils.isBlank(users.getNickname())){
                users.setNickname(users.getUsername());
            }
            users.setPassword(MD5Utils.getMD5Str(users.getPassword()));
            users.setFansCounts(0);
            users.setReceiveLikeCounts(0);
            users.setFollowCounts(0);
            return iUsersService.saveUser(users);
        }else{
            return ServerResponse.createByErrorMsg("该账号已被注册");
        }
    }

    @ApiOperation(value = "用户登录接口",notes = "用户登录接口notes")
    @PostMapping("login")
    public ServerResponse usersLogin(Users users, HttpServletRequest req) throws Exception{
        if (StringUtils.isBlank(users.getUsername()) || StringUtils.isBlank(users.getPassword())){
            return ServerResponse.createByErrorMsg("账号和密码为空");
        }
        return iUsersService.login(users);
//        ServerResponse serverResponse = iUsersService.login(users);
//        if (serverResponse.getStatus() == 0){
//            String token = UUID.randomUUID().toString();
//            req.getSession().setAttribute("Token",token);
//            System.err.println("登陆成功-token："+token);
//            req.getSession().setAttribute("isLogin",true);
//            System.err.println("session设置成功");
//            return ServerResponse.createBySuccessAll("登录成功",serverResponse.getData());
//        }else {
//            return ServerResponse.createByErrorMsg("账号或密码错误");
//        }

//        if (iUsersService.login(users).getStatus() == 0){
//            String token = UUID.randomUUID().toString();
//            req.setAttribute("Token",token);
//            System.err.println("登陆成功-token："+token);
//            req.getSession().setAttribute("isLogin",true);
//            System.err.println("session设置成功");
//            return iUsersService.login(users);
//        }else{
//            return iUsersService.login(users);
//        }
    }

    @ApiOperation(value = "测试接口",notes = "测试接口notes")
    @GetMapping("test")
    public ServerResponse test(){
        return ServerResponse.createBySuccessMsg("测试成功");
    }
}
