package com.xtang.dao;

import com.xtang.vo.CommentsVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: short-video-back
 * @Date: 2019/8/12 16:47
 * @Author: xTang
 * @Description:
 */
@Repository
public interface CommentsMapperCustom {

    /**
     * 通过id查询所有留言
     * @param videoId videoId
     * @return CommentsVo
     */
    List<CommentsVo> queryAllComments(String videoId);
}
