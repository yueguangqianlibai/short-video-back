package com.xtang.dao;

import com.xtang.pojo.Users;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersMapper {
    int deleteByPrimaryKey(String id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    /**
     * 查询用户名是否存在
     * @param userName userName
     * @return
     */
    int queryName(String userName);

    /**
     * 登录查询
     * @param username username
     * @param password password
     * @return
     */
    Users selectLogin(@Param("username") String username, @Param("password")String password);

    /**
     * 用户受喜欢数量+1
     * @param userId userId
     */
    void addVideoLikeCount(String userId);

    /**
     * 用户受喜欢数量减1
     * @param userId userId
     */
    void reduceVideoLikeCount(String userId);

    /**
     * 增加粉丝数量
     * @param userId userId
     */
    void addFansCount(String userId);

    /**
     * 增加关注数量
     * @param userId userId
     */
    void addFollersCount(String userId);

    /**
     * 减少粉丝数量
     * @param userId userId
     */
    void reduceFansCount(String userId);

    /**
     * 减少关注数量
     * @param userId userId
     */
    void reduceFollersCount(String userId);


    /**
     * 查询指定用户的所有关注的用户
     * @param userId userId用户id
     * @return VideosVo对象
     */
    List<Users> selectUserFollowMansByUserId(String userId);


}