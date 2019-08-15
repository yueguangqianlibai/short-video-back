package com.xtang.dao;

import com.xtang.pojo.Users;
import com.xtang.pojo.Videos;
import com.xtang.vo.VideosVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideosMapper {
    int deleteByPrimaryKey(String id);

    int insert(Videos record);

    int insertSelective(Videos record);

    Videos selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Videos record);

    int updateByPrimaryKey(Videos record);

    /**
     * 查询指定用户的所有视频
     * @param userId userId
     * @return Videos对象
     */
    List<Videos> selectByUserId(String userId);

    /**
     * 查询指定用户的所有喜欢的视频视频
     * @param userId userId用户id
     * @return VideosVo对象
     */
    List<VideosVo> selectUserLikeVideoByUserId(String userId);


}