package com.xtang.service;

import com.xtang.pojo.Videos;
import com.xtang.utils.PagedResult;

/**
 * @program: short-video-back
 * @Date: 2019/7/13 17:04
 * @Author: xTang
 * @Description:
 */
public interface IVideoService {

    /**
     * 保存视频
     * @param videos
     */
    void saveVideo(Videos videos);

    /**
     * 修改视频的封面
     * @param videoId
     * @param coverId
     */
    void updateVideoCover(String videoId,String coverId);

    /**
     * 分页查询视频列表
     * @param pageNum
     * @param pageSize
     */
    PagedResult pagedResult(Integer pageNum, Integer pageSize);
}
