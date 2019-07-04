package com.xtang.common;

/**
 * @Author: xTang
 */
public enum ResponseCode {
    /**
     *
     */
    SUCCESS(0,"SUCCESS"),
    ERROR(1,"ERROR"),
    NEED_LOGIN(10,"需要登录"),
    ILLEGAL_ARGUMENT(2,"非法参数"),
    VALIDATION_ERROR(20,"bean验证出错"),
    TOKEN_ERROR(30,"token验证出错"),
    ABNORMAL_OUTPUT_INFORMATION(40,"异常输出信息");

    private final int code;
    private final String desc;


    ResponseCode(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }
}
