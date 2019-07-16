package com.xtang.service;

import com.xtang.pojo.Videos;

/**
 * @program: short-video-back
 * @Date: 2019/7/13 17:04
 * @Author: xTang
 * @Description:
 */
public interface IVideoService {

    void saveVideo(Videos videos);

    void updateVideoCover(String videoId,String coverId);
}
