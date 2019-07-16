package com.xtang.service.impl;

import com.xtang.dao.VideosMapper;
import com.xtang.pojo.Videos;
import com.xtang.service.IVideoService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: short-video-back
 * @Date: 2019/7/13 17:04
 * @Author: xTang
 * @Description:
 */
@Service
public class VideoServiceImpl implements IVideoService {

    @Autowired
    private VideosMapper videosMapper;



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void saveVideo(Videos videos) {
        videosMapper.insertSelective(videos);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void updateVideoCover(String videoId, String coverPath) {
        Videos videos = new Videos();
        videos.setId(videoId);
        videos.setCoverPath(coverPath);
        videosMapper.updateByPrimaryKeySelective(videos);
    }
}
