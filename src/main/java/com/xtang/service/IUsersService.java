package com.xtang.service;

import com.xtang.common.ServerResponse;
import com.xtang.pojo.Users;
import org.springframework.stereotype.Repository;

/**
 * @program: short-video-back
 * @Date: 2019/7/4 18:26
 * @Author: xTang
 * @Description:
 */
public interface IUsersService {

    /**
     * 查username是否重复
     */
    boolean queryUserNameIsExist(String username);

    /**
     * 新增用户
     */
    ServerResponse saveUser(Users users);

    /**
     * 登录
     */
    ServerResponse login(Users users) throws Exception;

    /**
     * 更新用户信息
     */
    void updateUserInfo(Users users);

    /**
     * 获取用户信息
     * @param UserId
     * @return
     */
    Users queryUsersInfo(String UserId);
}
