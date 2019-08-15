package com.xtang.controller;

import com.xtang.common.ServerResponse;
import com.xtang.pojo.Users;
import com.xtang.pojo.UsersReport;
import com.xtang.service.IUsersService;
import com.xtang.vo.PublisherVideoVo;
import com.xtang.vo.UsersVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: short-video-back
 * @Date: 2019/7/8 19:48
 * @Author: xTang
 * @Description:
 */
@RestController
@Api(value = "用户业务相关服务的接口", tags = {"用户业务相关服务的响应接口(Controller)"})
@RequestMapping("userService")
public class UserServerController {

    @Autowired
    private IUsersService iUsersService;

    @ApiOperation(value = "用户上传头像", notes = "用户上传头像接口服务")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true,
            dataType = "String", paramType = "query")
    @PostMapping("uploadFace")
    public ServerResponse uploadFace(String userId, @RequestParam("file") MultipartFile[] files) throws Exception {

        if (StringUtils.isBlank(userId)) {
            return ServerResponse.createByErrorMsg("用户ID为空");
        }

        //文件保存的命名空间
        String fileSpace = "G:/short-video-back-dev";
        //保存到数据库的相对路径
        String uploadPathDB = "/" + userId + "/face";
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try {
            if (files != null && files.length > 0) {
                //获取文件名
                String fileName = files[0].getOriginalFilename();
                if (StringUtils.isNoneBlank(fileName)) {
                    //文件上传的最终保存路径
                    String finalFacePath = fileSpace + uploadPathDB + "/" + fileName;
                    //设置数据库保存的路径
                    uploadPathDB += ("/" + fileName);

                    File outFile = new File(finalFacePath);
                    if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                        //创建父文件夹
                        outFile.getParentFile().mkdirs();
                    }

                    fileOutputStream = new FileOutputStream(outFile);
                    inputStream = files[0].getInputStream();
                    IOUtils.copy(inputStream, fileOutputStream);
                }
            } else {
                System.err.println("第一处错误");
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
        Users users = new Users();
        users.setId(userId);
        users.setFaceImage(uploadPathDB);
        iUsersService.updateUserInfo(users);
        return ServerResponse.createBySuccessAll("上传成功", uploadPathDB);
    }

    @ApiOperation(value = "查询用户信息", notes = "查询用户信息接口服务")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true,
            dataType = "String", paramType = "query")
    @PostMapping("queryInfo")
    public ServerResponse queryUserInfo(String userId, String fanId) throws Exception {
        if (StringUtils.isBlank(userId)) {
            return ServerResponse.createByErrorMsg("未获取到用户信息");
        }
        Users users = iUsersService.queryUsersInfo(userId);
        UsersVo usersVo = new UsersVo();

        BeanUtils.copyProperties(users, usersVo);
        usersVo.setFollow(iUsersService.queryIfFollow(userId, fanId));
        return ServerResponse.createBySuccessAll("查询用户信息成功", usersVo);
    }

    @PostMapping("queryPublisher")
    public ServerResponse queryPublisher(String loginUserId, String videoId, String publisherUserId) {
        if (StringUtils.isBlank(publisherUserId)) {
            return ServerResponse.createByError();
        }
        //查询视频发布者的信息
        Users userInfo = iUsersService.queryUsersInfo(publisherUserId);
        UsersVo publisher = new UsersVo();
        BeanUtils.copyProperties(userInfo, publisher);
        //2.查询当前登录者和视频的点赞关系
        Boolean userLikeVideo = iUsersService.isUserLikeVideo(loginUserId, videoId);

        PublisherVideoVo bean = new PublisherVideoVo();
        bean.setPublisher(publisher);
        bean.setUserLikeVideo(userLikeVideo);

        return ServerResponse.createBySuccessData(bean);
    }

    @PostMapping("beYourFans")
    public ServerResponse beYourFans(String userId, String fanId) {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(fanId)) {
            return ServerResponse.createByErrorMsg("用户id或粉丝id为空");
        }
        iUsersService.saveUserFanRelation(userId, fanId);
        return ServerResponse.createBySuccessMsg("关注成功..");
    }

    @PostMapping("notBeYourFans")
    public ServerResponse notBeYourFans(String userId, String fanId) {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(fanId)) {
            return ServerResponse.createByErrorMsg("用户id或粉丝id为空");
        }
        iUsersService.delUserFanRelation(userId, fanId);
        return ServerResponse.createBySuccessMsg("取消关注成功..");
    }

    @PostMapping("reportUser")
    public ServerResponse reportUser(UsersReport usersReport) throws Exception {
        iUsersService.reportUser(usersReport);
        return ServerResponse.createBySuccessMsg("举报成功");
    }

}
