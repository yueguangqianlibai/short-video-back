package com.xtang.service;

import com.xtang.common.ServerResponse;
import com.xtang.pojo.Users;

/**
 * @program: short-video-back
 * @Date: 2019/7/4 18:26
 * @Author: xTang
 * @Description:
 */
public interface IUsersService {


    boolean queryUserNameIsExist(String username);

    ServerResponse saveUser(Users users);

    ServerResponse login(Users users) throws Exception;
}
