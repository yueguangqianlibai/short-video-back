package com.xtang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xtang.dao.VideosMapper;
import com.xtang.dao.VideosMapperCustom;
import com.xtang.pojo.Videos;
import com.xtang.service.IVideoService;
import com.xtang.utils.PagedResult;
import com.xtang.vo.VideosVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Autowired
    private VideosMapperCustom videosMapperCustom;


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

    @Override
    public PagedResult pagedResult(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum,pageSize);
        List<VideosVo> videosVos =  videosMapperCustom.queryAllVideos();

        PageInfo<VideosVo> pageList = new PageInfo<>(videosVos);

        PagedResult pagedResult = new PagedResult();
        pagedResult.setPageNum(pageNum);
        pagedResult.setTotal(pageList.getPages());
        pagedResult.setRows(videosVos);
        pagedResult.setRecords(pageList.getTotal());

        return pagedResult;
    }
}
