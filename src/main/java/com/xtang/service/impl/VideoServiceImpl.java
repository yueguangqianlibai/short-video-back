package com.xtang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xtang.dao.*;
import com.xtang.pojo.*;
import com.xtang.service.IVideoService;
import com.xtang.utils.PagedResult;
import com.xtang.utils.TimeAgoUtils;
import com.xtang.vo.CommentsVo;
import com.xtang.vo.VideosVo;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

    @Autowired
    private SearchRecordsMapper searchRecordsMapper;

    @Autowired
    private UsersLikeVideosMapper usersLikeVideosMapper;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private Sid sid;

    @Autowired
    private CommentsMapper commentsMapper;

    @Autowired
    private CommentsMapperCustom commentsMapperCustom;

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

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public PagedResult pagedResult(Videos videos,
                                   Integer isSaveRecord,
                                   Integer pageNum, Integer pageSize) {
        //保存热搜词
        String desc = videos.getVideoDesc();
        if (isSaveRecord != null && isSaveRecord == 1) {
            SearchRecords records = new SearchRecords();
            records.setId(sid.nextShort());
            records.setContent(desc);
            searchRecordsMapper.insert(records);
        }

        PageHelper.startPage(pageNum, pageSize);
        List<VideosVo> videosVos = videosMapperCustom.queryAllVideos(desc);
        PageInfo<VideosVo> pageList = new PageInfo<>(videosVos);
        PagedResult pagedResult = new PagedResult();
        pagedResult.setPageNum(pageNum);
        pagedResult.setTotal(pageList.getPages());
        pagedResult.setRows(videosVos);
        pagedResult.setRecords(pageList.getTotal());

        return pagedResult;
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public List<String> getHotWords() {
        return searchRecordsMapper.getHotWords();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void userLikeVideo(String userId, String videoId, String videoCreatorId) {
        //1.保存用户和视频的喜欢点赞关联关系表
        String likeId = sid.nextShort();
        UsersLikeVideos usersLikeVideos = new UsersLikeVideos();
        usersLikeVideos.setId(likeId);
        usersLikeVideos.setUserId(userId);
        usersLikeVideos.setVideoId(videoId);
        usersLikeVideosMapper.insert(usersLikeVideos);
        //2.视频喜欢数量累加
        videosMapperCustom.addVideoLikeCount(videoId);
        //3.用户受喜欢的数量累加
        usersMapper.addVideoLikeCount(videoCreatorId);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void userUnlikeVideo(String userId, String videoId, String videoCreatorId) {
        //1.删除用户和视频的喜欢点赞关联关系表
        UsersLikeVideos usersLikeVideos = usersLikeVideosMapper.selectByObject(userId, videoId);
        usersLikeVideosMapper.deleteByPrimaryKey(usersLikeVideos.getId());
        //2.视频喜欢数量累减
        videosMapperCustom.reduceVideoLikeCount(videoId);
        //3.用户受喜欢的数量累减
        usersMapper.reduceVideoLikeCount(videoCreatorId);
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public PagedResult userWorksVideo(String userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Videos> videos = videosMapper.selectByUserId(userId);
        PageInfo<Videos> pageList = new PageInfo<>(videos);
        PagedResult pagedResult = new PagedResult();
        pagedResult.setPageNum(pageNum);
        pagedResult.setTotal(pageList.getPages());
        pagedResult.setRows(videos);
        pagedResult.setRecords(pageList.getTotal());
        return pagedResult;
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public PagedResult getUserLikesList(String userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<VideosVo> videosVos = videosMapper.selectUserLikeVideoByUserId(userId);
        PageInfo<VideosVo> pageList = new PageInfo<>(videosVos);
        PagedResult pagedResult = new PagedResult();
        pagedResult.setPageNum(pageNum);
        pagedResult.setTotal(pageList.getPages());
        pagedResult.setRows(videosVos);
        pagedResult.setRecords(pageList.getTotal());
        return pagedResult;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void saveComment(Comments comments) {
        comments.setId(sid.nextShort());
        comments.setCreateTime(new Date());
        comments.setUpdateTime(new Date());
        commentsMapper.insert(comments);
    }

    @Override
    public PagedResult getAllCommnets(String videoId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CommentsVo> commentsList = commentsMapperCustom.queryAllComments(videoId);
        for (CommentsVo c : commentsList){
            String timeAgo = TimeAgoUtils.format(c.getCreateTime());
            c.setTimeAgoStr(timeAgo);
        }

        PageInfo<CommentsVo> pageList = new PageInfo<>(commentsList);
        PagedResult pagedResult = new PagedResult();
        pagedResult.setPageNum(pageList.getPageNum());
        pagedResult.setTotal(pageList.getPages());
        pagedResult.setRows(commentsList);
        pagedResult.setRecords(pageList.getTotal());
        return pagedResult;
    }
}
