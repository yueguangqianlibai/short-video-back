package com.xtang.service;

import com.xtang.common.ServerResponse;
import com.xtang.pojo.Users;
import com.xtang.pojo.UsersReport;
import com.xtang.utils.PagedResult;

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
     * @param UserId UserId
     * @return
     */
    Users queryUsersInfo(String UserId);

    /**
     * 查询用户是否已经点赞
     * @param userId userId
     * @param videoId videoId
     * @return
     */
    boolean isUserLikeVideo(String userId,String videoId);

    /**
     * 增加用户和粉丝的关系
     * @param userId userId
     * @param fanId fanId
     */
    void saveUserFanRelation(String userId,String fanId);

    /**
     * 删除用户和粉丝的关系
     * @param userId userId
     * @param fanId fanId
     */
    void delUserFanRelation(String userId,String fanId);

    /**
     * 查询用户是否关注
     * @param userId userId
     * @param fanId fanId
     * @return
     */
    boolean queryIfFollow(String userId,String fanId);

    /**
     * 查询指定用户所有关注的用户
     * @param userId 用户ID
     * @param pageNum 分页页数
     * @param pageSize 每页展示数量
     * @return PagedResult分页后的users对象
     */
    PagedResult getMyFollowList(String userId, Integer pageNum, Integer pageSize);

    /**
     * 举报用户
     * @param usersReport usersReport
     */
    void reportUser(UsersReport usersReport);

}
