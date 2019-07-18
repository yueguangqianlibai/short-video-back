package com.xtang.dao;

import com.xtang.vo.VideosVo;
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

    public List<VideosVo> queryAllVideos();

}
