package com.xtang.common;

/**
 * @program: short-video-back
 * @Date: 2019/7/13 16:59
 * @Author: xTang
 * @Description:
 */
public enum VideoStatusEmum {

    /*
    *发布成功
    * 禁止播放
    */
    SUCCESS(1),
    FORBID(2);

    public final int value;

    VideoStatusEmum(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
