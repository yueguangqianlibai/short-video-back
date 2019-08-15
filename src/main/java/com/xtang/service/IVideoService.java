package com.xtang.service;

import com.xtang.pojo.Comments;
import com.xtang.pojo.Videos;
import com.xtang.utils.PagedResult;

import java.util.List;

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
     * @param videos 视频对象
     * @param isSaveRecord 是否保存热搜词
     * @param pageNum 分页页数
     * @param pageSize 分页页码
     * @return
     */
    PagedResult pagedResult(Videos videos,
                            Integer isSaveRecord,Integer pageNum, Integer pageSize);

    /**
     * 拿到热搜词
     * @return
     */
    List<String> getHotWords();

    /**
     * 用户喜欢视频
     * @param userId 用户id
     * @param videoId 视频id
     * @param videoCreatorId 视频创建者的id
     */
    void userLikeVideo(String userId,String videoId,String videoCreatorId);

    /**
     * 用户取消喜欢视频
     * @param userId 用户id
     * @param videoId 视频id
     * @param videoCreatorId 视频创建者的id
     */
    void userUnlikeVideo(String userId,String videoId,String videoCreatorId);

    /**
     * 查询指定用户所有视频
     * @param userId 用户ID
     * @param pageNum 分页页数
     * @param pageSize 每页展示数量
     * @return PagedResult分页后的video对象
     */
    PagedResult userWorksVideo(String userId,Integer pageNum, Integer pageSize);

    /**
     * 查询指定用户所有喜欢的视频
     * @param userId 用户ID
     * @param pageNum 分页页数
     * @param pageSize 每页展示数量
     * @return PagedResult分页后的video对象
     */
    PagedResult getUserLikesList(String userId,Integer pageNum, Integer pageSize);

    /**
     * 留言功能开发
     * @param comments comments
     */
    void saveComment(Comments comments);

    /**
     * 获取留言列表
     * @param videoId videoId
     * @param pageNum pageNum
     * @param pageSize pageSize
     * @return PagedResult
     */
    PagedResult getAllCommnets(String videoId,Integer pageNum,Integer pageSize);

}
