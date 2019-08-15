package com.xtang.dao;

import com.xtang.vo.VideosVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: short-video-back
 * @Date: 2019/7/17 22:04
 * @Author: xTang
 * @Description:
 */
@Repository
public interface VideosMapperCustom {

    /**
     * 查询视频返回自定义vo类(nickname+faceImage)
     * @param videoDesc
     * @return
     */
    List<VideosVo> queryAllVideos(@Param("videoDesc") String videoDesc);

    /**
     * 对喜欢的视频数量进行累加
     * @param videoId
     */
    void addVideoLikeCount(String videoId);

    /**
     * 对喜欢的视频数量进行累减
     * @param videoId
     */
    void reduceVideoLikeCount(String videoId);

}
