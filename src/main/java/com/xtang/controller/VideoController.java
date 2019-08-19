package com.xtang.controller;

import com.xtang.common.ServerResponse;
import com.xtang.common.VideoStatusEmum;
import com.xtang.pojo.Bgm;
import com.xtang.pojo.Comments;
import com.xtang.pojo.Videos;
import com.xtang.service.IBgmService;
import com.xtang.service.IVideoService;
import com.xtang.utils.MergeVideoMp3;
import com.xtang.utils.PagedResult;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * @program: short-video-back
 * @Date: 2019/7/9 23:33
 * @Author: xTang
 * @Description:
 */

@RestController
@Api(value = "视频服务的接口", tags = {"视频相关服务的响应接口(Controller)"})
@RequestMapping("video")
public class VideoController {

    @Autowired
    private Sid sid;

    @Autowired
    private IBgmService iBgmService;

    @Autowired
    private IVideoService iVideoService;

    @ApiOperation(value = "用户上传视频", notes = "用户上传视频接口服务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true,
                    dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "bgmId", value = "背景音乐ID", required = false,
                    dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "videoSeconds", value = "背景音乐播放长度", required = true,
                    dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "videoWidth", value = "视频宽度", required = true,
                    dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "videoHeight", value = "视频高度", required = true,
                    dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "desc", value = "视频描述", required = false,
                    dataType = "String", paramType = "form")
    })
    @PostMapping(value = "uploadVideo", headers = "content-type=multipart/form-data")
    public ServerResponse uploadVideo(String userId, String bgmId, double videoSeconds,
                                      int videoWidth, int videoHeight, String desc,
                                      @ApiParam(value = "短视频", required = true) MultipartFile file) throws Exception {

        if (StringUtils.isBlank(userId)) {
            return ServerResponse.createByErrorMsg("用户Id不可为空");
        }

        //文件保存的命名空间 文件会保存在此位置
        //String fileSpace = "G:/short-video-back-dev";
        //最终的视频链接
        String finalVideoPath = "";

        String coverPathDB = "";

        String fileName = "";
        //保存的相对路径 非最终路径
        String uploadPathDB = "/" + userId + "/video";

        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try {
            if (file != null) {
                //获取文件名
                fileName = file.getOriginalFilename();
                if (StringUtils.isNoneBlank(fileName)) {
                    //文件上传的保存路径
                    //G:/short-video-back-dev/userId/video/fileName
                    finalVideoPath = BasicController.FILE_SPACE + uploadPathDB + "/" + fileName;
                    //设置数据库保存的路径
                    uploadPathDB += ("/" + fileName);
                    File outFile = new File(finalVideoPath);
                    if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                        //创建父文件夹
                        outFile.getParentFile().mkdirs();
                    }
                    fileOutputStream = new FileOutputStream(outFile);
                    inputStream = file.getInputStream();
                    IOUtils.copy(inputStream, fileOutputStream);
                }
            } else {
                return ServerResponse.createByErrorMsg("上传失败...");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMsg("上传失败...");
        } finally {
            if (fileOutputStream != null) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }

        // TODO: 2019/7/18 没有bgm的视频截图并上传
        if (StringUtils.isBlank(bgmId)) {
            MergeVideoMp3 getCover = new MergeVideoMp3(BasicController.FFMPEG_EXE);
            String coverResultPath = getCover.getCoverOfVideoNoBgm(finalVideoPath, finalVideoPath.replace(".mp4", ".jpg"));
            StringBuffer sb = new StringBuffer(coverResultPath);
            int i = sb.indexOf(fileName.replace(".mp4", ".jpg"));
            coverResultPath = sb.substring(i);
            coverPathDB = "/" + userId + "/video" + "/" + coverResultPath;
        }

        // 判断bgmId是否为空，如果不为空，
        // 那就查询bgm的信息，并且合并视频，生产新的视频
        if (StringUtils.isNoneBlank(bgmId)) {
            Bgm bgm = iBgmService.selectById(bgmId);
            String mp3InputPath = BasicController.FILE_SPACE + bgm.getPath();

            MergeVideoMp3 tool = new MergeVideoMp3(BasicController.FFMPEG_EXE);
            //finalVideoPath--用户刚上传时候视频的路径

            String videoInputPath = finalVideoPath;
            String videoName = UUID.randomUUID().toString();
            String videoOutputName = videoName + ".mp4";
            uploadPathDB = "/" + userId + "/video" + "/" + videoOutputName;
            finalVideoPath = BasicController.FILE_SPACE + uploadPathDB;
            List<String> videoAndCoverPath = tool.userTheMethod(videoInputPath, mp3InputPath, finalVideoPath, videoSeconds);
            String trueUrl = videoAndCoverPath.get(0);
            String coverPath = videoAndCoverPath.get(1);
            StringBuffer sb = new StringBuffer(trueUrl);
            int i = sb.indexOf(videoName);
            trueUrl = sb.substring(i);
            uploadPathDB = "/" + userId + "/video" + "/" + trueUrl;

            StringBuffer sbCoverPath = new StringBuffer(coverPath);
            int iCoverPath = sbCoverPath.indexOf(videoName);
            coverPath = sbCoverPath.substring(iCoverPath);
            coverPathDB = "/" + userId + "/video" + "/" + coverPath;
        }

        // TODO: 2019/7/13 保存视频信息到数据库
        Videos videos = new Videos();
        videos.setId(sid.nextShort());
        videos.setUserId(userId);
        videos.setAudioId(bgmId);
        videos.setVideoSeconds((float) videoSeconds);
        videos.setVideoHeigh(videoHeight);
        videos.setVideoWidth(videoWidth);
        videos.setVideoDesc(desc);
        videos.setVideoPath(uploadPathDB);
        videos.setCoverPath(coverPathDB);
        videos.setStatus(VideoStatusEmum.SUCCESS.getValue());
        iVideoService.saveVideo(videos);
        return ServerResponse.createBySuccessData(videos);

    }

//    @ApiOperation(value = "上传视频封面", notes = "上传视频封面的接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "videoId", value = "指定视频的Id", required = true,
//                    dataType = "String", paramType = "form"),
//            @ApiImplicitParam(name = "userId", value = "指定视频所属用户的Id", required = true,
//                    dataType = "String", paramType = "form")
//    })
//    @PostMapping(value = "uploadCover", headers = "content-type=multipart/form-data")
//    public ServerResponse uploadVideoCover(String videoId, String userId,
//                                           @ApiParam(value = "视频封面", required = true)
//                                                   MultipartFile file) throws Exception {
//        if (StringUtils.isBlank(videoId) || StringUtils.isBlank(userId)) {
//            return ServerResponse.createByErrorMsg("视频id或用户id为空");
//        }
//
//        //文件保存的命名空间 文件会保存在此位置
//        //String fileSpace = "G:/short-video-back-dev";
//        //最终的视频链接
//        String finalCoverPath = "";
//        //保存的相对路径 非最终路径
//        String uploadPathDB = "/" + userId + "/video";
//        FileOutputStream fileOutputStream = null;
//        InputStream inputStream = null;
//        try {
//            if (file != null) {
//                //获取文件名
//                String fileName = file.getOriginalFilename();
//                if (StringUtils.isNoneBlank(fileName)) {
//                    //文件上传的保存路径
//                    //G:/short-video-back-dev/userId/video/fileName
//                    finalCoverPath = BasicController.FILE_SPACE + uploadPathDB + "/" + fileName;
//                    //设置数据库保存的路径
//                    uploadPathDB += ("/" + fileName);
//                    File outFile = new File(finalCoverPath);
//                    if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
//                        //创建父文件夹
//                        outFile.getParentFile().mkdirs();
//                    }
//                    fileOutputStream = new FileOutputStream(outFile);
//                    inputStream = file.getInputStream();
//                    IOUtils.copy(inputStream, fileOutputStream);
//                }
//            } else {
//                return ServerResponse.createByErrorMsg("上传失败...");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ServerResponse.createByErrorMsg("上传失败...");
//        } finally {
//            if (fileOutputStream != null) {
//                fileOutputStream.flush();
//                fileOutputStream.close();
//            }
//        }
//
//        iVideoService.updateVideoCover(videoId, uploadPathDB);
//
//        return ServerResponse.createBySuccessMsg("上传成功");
//
//    }

    /**
     * isSaveRecord 1-需要保存，0-不需要保存
     *
     * @param videos       videos
     * @param isSaveRecord isSaveRecord
     * @param pageNum      pageNum
     * @return ServerResponse
     */
    @PostMapping(value = "showAllVideo")
    public ServerResponse showAllVideo(Videos videos,
                                       Integer isSaveRecord,
                                       Integer pageNum) {
        if (pageNum == null) {
            pageNum = 1;
        }
        PagedResult result = iVideoService.pagedResult(videos, isSaveRecord, pageNum, BasicController.PAGE_SIZE);
        return ServerResponse.createBySuccessData(result);
    }

    @PostMapping(value = "hot")
    public ServerResponse hotSearch() throws Exception {
        return ServerResponse.createBySuccessData(iVideoService.getHotWords());
    }

    @PostMapping(value = "userLike")
    public ServerResponse userLike(String userId, String videoId, String videoCreatorId) throws Exception {
        iVideoService.userLikeVideo(userId, videoId, videoCreatorId);
        return ServerResponse.createBySuccess();
    }

    @PostMapping(value = "userUnlike")
    public ServerResponse userUnlike(String userId, String videoId, String videoCreatorId) throws Exception {
        iVideoService.userUnlikeVideo(userId, videoId, videoCreatorId);
        return ServerResponse.createBySuccess();
    }

    @PostMapping(value = "showUserWorks")
    public ServerResponse showUserWorks(String userId, Integer pageNum, Integer pageSize) throws Exception {
        PagedResult pagedResult = iVideoService.userWorksVideo(userId, pageNum, pageSize);
        return ServerResponse.createBySuccessData(pagedResult);
    }

    @PostMapping(value = "getUserLikesList")
    public ServerResponse getUserLikesList(String userId, Integer pageNum, Integer pageSize) throws Exception {
        PagedResult pagedResult = iVideoService.getUserLikesList(userId, pageNum, pageSize);
        return ServerResponse.createBySuccessData(pagedResult);
    }

    @PostMapping(value = "saveComment")
    public ServerResponse saveComment(Comments comments, String fatherCommentId, String toUserId) throws Exception {
        if (StringUtils.isNoneBlank(fatherCommentId) && StringUtils.isNoneBlank(toUserId)) {
            comments.setFatherCommentId(fatherCommentId);
            comments.setToUserId(toUserId);
        }
        iVideoService.saveComment(comments);
        return ServerResponse.createBySuccessData("保存成功");
    }

    @PostMapping(value = "getVideoComments")
    public ServerResponse getVideoComments(String videoId, Integer pageNum, Integer pageSize) throws Exception {
        if (StringUtils.isBlank(videoId)) {
            return ServerResponse.createByError();
        }
        if (pageNum == 0) {
            pageNum = 1;
        }
        if (pageSize == 0) {
            pageSize = 10;
        }

        PagedResult commentsList = iVideoService.getAllCommnets(videoId, pageNum, pageSize);
        return ServerResponse.createBySuccessData(commentsList);
    }
}


//}
/**///