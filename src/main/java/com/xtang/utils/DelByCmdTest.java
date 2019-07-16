package com.xtang.utils;

/**
 * @program: short-video-back
 * @Date: 2019/7/16 10:15
 * @Author: xTang
 * @Description:
 */
public class DelByCmdTest {
    public static void main(String[] args) {
        Runtime run = Runtime.getRuntime();
        try {
            String cmd="cmd   /c   del "+"G:\\ffmpeg\\bin\\new.mp4";
            //s 除目录本身外，还将删除指定目录下的所有子目录和文件，用于删除目录树。
            //q 安静模式
            //s 删除目录树时不要求确认
            Process p = run.exec(cmd);
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
