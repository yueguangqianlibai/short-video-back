package com.xtang.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: short-video-back
 * @Date: 2019/7/12 10:23
 * @Author: xTang
 * @Description:
 */
public class MergeVideoMp3 {

    private String ffmpegEXE;

    private String trueUrl;

    /**
     * 刚上传上来的视频
     * 删掉音轨后的视频
     */
    private String videoPath1 = "";

    private String videoPath2 = "";

    public MergeVideoMp3(String ffmpegEXE) {
        super();
        this.ffmpegEXE = ffmpegEXE;
    }

    /**
     * 视频合并音频
     */
    public void convertor(String videoInputPath, String mp3InputPath,
                          String videoOutputPath, double seconds) throws Exception {
        //ffmpeg.exe -i a.mp4 -i bgm.mp3 -t 7 -y new.mp4
        List<String> command = new ArrayList<>();
        command.add(ffmpegEXE);
        command.add("-i");
        command.add(videoInputPath);
        command.add("-i");
        command.add(mp3InputPath);
        command.add("-t");
        command.add(String.valueOf(seconds));
        command.add("-y");
        command.add(videoOutputPath);

        this.commonMethod(command);
        trueUrl = videoOutputPath;

        this.deleteOldVideo1(videoPath1);
        this.deleteOldVideo2(videoPath2);
    }

    public void deleteOldVideo1(String videoPath1) throws Exception {
        String trueOldVideoUrl = videoPath1.replace("/","\\");
        String cmdMain = "cmd /c del ";
        Runtime run = Runtime.getRuntime();
        String cmd=cmdMain+trueOldVideoUrl;
        Process p = run.exec(cmd);
        System.err.println("videoPath1---"+cmd);
        p.waitFor();
    }

    public void deleteOldVideo2(String videoPath2) throws Exception {
        String trueOldVideoUrl = videoPath2.replace("/","\\");
        String cmdMain = "cmd /c del ";
        Runtime run = Runtime.getRuntime();
        String cmd=cmdMain+trueOldVideoUrl;
        Process p = run.exec(cmd);
        System.err.println("videoPath2---"+cmd);
        p.waitFor();
    }


    public void deleteBackgroundMusic(String videoInputPath, String mp3InputPath,
                                      String videoOutputPath, double seconds) throws Exception {

        videoPath1 = videoInputPath;
        videoPath2 = videoOutputPath;
        //ffmpeg.exe -i 2.mp4 -an new.mp4
        int i = videoOutputPath.indexOf(".mp4");
        StringBuffer sb = new StringBuffer(videoOutputPath);
        sb.insert(i, "xiaotangshortvideos");
        List<String> command = new ArrayList<>();
        command.add(ffmpegEXE);
        command.add("-i");
        command.add(videoInputPath);
        command.add("-an");
        command.add(videoOutputPath);
        String newVideoUrl = videoOutputPath;
        this.commonMethod(command);
        this.convertor(newVideoUrl, mp3InputPath, sb.toString(), seconds);
    }

//    public void delectOldVideo(String url) throws Exception{
//        List<String> command = new ArrayList<>();
//        command.add("del");
//        command.add(url);
//        for (String c : command) {
//
//        }
//        this.commonMethod(command);
//    }

    public void commonMethod(List<String> cmdWord) throws Exception {
        ProcessBuilder builder = new ProcessBuilder(cmdWord);
        Process process = builder.start();
        InputStream errorSteam = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorSteam);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
        }
        if (bufferedReader != null) {
            bufferedReader.close();
        }
        if (inputStreamReader != null) {
            inputStreamReader.close();
        }
        if (errorSteam != null) {
            errorSteam.close();
        }
    }

    public String userTheMethod(String videoInputPath, String mp3InputPath,
                                String videoOutputPath, double seconds) throws Exception {
        this.deleteBackgroundMusic(videoInputPath, mp3InputPath, videoOutputPath, seconds);
        return trueUrl;
    }

    public static void main(String[] args) {
//        MergeVideoMp3 ffMpeg = new MergeVideoMp3("G:\\ffmpeg\\bin\\ffmpeg.exe");
//        try {
//            ffMpeg.convertor("G:\\ffmpeg\\bin\\3.mp4",
//                    "G:\\ffmpeg\\bin\\bgm.mp3",
//                    "G:\\ffmpeg\\bin\\1907121150.mp4",7);
//            ffMpeg.deleteBackgroundMusic("G:\\ffmpeg\\bin\\2.mp4",
//                    "G:\\ffmpeg\\bin\\1.mp3",
//                    "G:\\ffmpeg\\bin\\new.mp4", 4);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
}
