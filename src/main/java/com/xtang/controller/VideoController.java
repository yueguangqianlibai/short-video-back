package com.xtang.controller;

import com.xtang.common.ServerResponse;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
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
 * @Date: 2019/7/9 23:33
 * @Author: xTang
 * @Description:
 */

@RestController
@Api(value = "视频服务的接口", tags = {"视频相关服务的响应接口(Controller)"})
@RequestMapping("video")
public class VideoController {


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
    @PostMapping(value = "uploadVideo",headers = "content-type=multipart/form-data")
    public ServerResponse uploadVideo(String userId, String bgmId, double videoSeconds,
                                     int videoWidth,int videoHeight,String desc,
                                     @ApiParam(value = "短视频",required = true) MultipartFile file) throws Exception {

        if(StringUtils.isBlank(userId)){
            return ServerResponse.createByErrorMsg("用户ID为空");
        }

        //文件保存的命名空间
        String fileSpace = "G:/short-video-back-dev";
        //保存到数据库的相对路径
        String uploadPathDB = "/" + userId + "/video";
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try {
            if (file != null) {
                //获取文件名
                String fileName = file.getOriginalFilename();
                if (StringUtils.isNoneBlank(fileName)) {
                    //文件上传的最终保存路径
                    String finalVideoPath = fileSpace + uploadPathDB + "/" + fileName;
                    //设置数据库保存的路径
                    uploadPathDB += ("/" + fileName);

                    File outFile = new File(finalVideoPath);
                    if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()){
                        //创建父文件夹
                        outFile.getParentFile().mkdirs();
                    }

                    fileOutputStream = new FileOutputStream(outFile);
                    inputStream = file.getInputStream();
                    IOUtils.copy(inputStream,fileOutputStream);
                }
            }else{
                System.err.println("第一处错误");
                return ServerResponse.createByErrorMsg("上传失败...");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMsg("上传失败...");
        }finally {
            if (fileOutputStream != null){
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }
        return ServerResponse.createBySuccessAll("上传成功",uploadPathDB);
    }
}
