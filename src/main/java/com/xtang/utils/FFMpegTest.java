package com.xtang.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: short-video-back
 * @Date: 2019/7/10 14:49
 * @Author: xTang
 * @Description:
 */
public class FFMpegTest {

    private String ffmpegEXE;

    public FFMpegTest(String ffmpegEXE) {
        super();
        this.ffmpegEXE = ffmpegEXE;
    }

    public void convertor(String videoInputPath,String videoOutputPath) throws Exception{
//        ffmpeg -i input.mp4 output.mp4
        //执行cmd命令
        List<String> command = new ArrayList<>();
        command.add(ffmpegEXE);
        command.add("-i");
        command.add(videoInputPath);
        command.add(videoOutputPath);
        for (String c : command){
            System.err.println(c);
        }
        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();

        InputStream errorSteam = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorSteam);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String line = "";
        while( (line = bufferedReader.readLine()) != null ){
        }
        if (bufferedReader != null){
            bufferedReader.close();
        }
        if (inputStreamReader != null){
            inputStreamReader.close();
        }
        if (errorSteam != null){
            errorSteam.close();
        }
    }


    public static void main(String[] args) {
        FFMpegTest ffMpeg = new FFMpegTest("G:\\ffmpeg\\bin\\ffmpeg.exe");
        try {
            ffMpeg.convertor("G:\\short-video-back-dev\\videos\\3.mp4",
                            "G:\\short-video-back-dev\\videos\\3-output.avi");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
