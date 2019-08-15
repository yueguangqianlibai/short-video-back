package com.xtang.dao;

import com.xtang.pojo.UsersLikeVideos;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersLikeVideosMapper {
    int deleteByPrimaryKey(String id);

    int insert(UsersLikeVideos record);

    int insertSelective(UsersLikeVideos record);

    UsersLikeVideos selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UsersLikeVideos record);

    int updateByPrimaryKey(UsersLikeVideos record);

    /**
     * 通过userId 和 videoId 查询userlIkeVideos对象
     * @param userId userId
     * @param videoId videoId
     * @return
     */
    UsersLikeVideos selectByObject(@Param("userId") String userId,@Param("videoId") String videoId);
}