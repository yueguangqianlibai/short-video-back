package com.xtang.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * @Author: xTang
 * @JsonSerialize: 保证序列化json的时候,如果是null的对象,key也会消失
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> implements Serializable {

    private int status;
    private String msg;
    private T data;

    private ServerResponse(int status){
        this.status = status;
    }

    private ServerResponse(int status,T data){
        this.status = status;
        this.data = data;
    }

    private ServerResponse(int status,String msg,T data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private ServerResponse(int status,String msg){
        this.status = status;
        this.msg = msg;
    }

    @JsonIgnore
    public boolean isSuccess(){
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public int getStatus(){
        return status;
    }

    public String getMsg(){
        return msg;
    }

    public T getData(){
        return data;
    }

    public static <T> ServerResponse<T> createBySuccess(){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> createBySuccessMsg(String msg){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }

    public static <T> ServerResponse<T> createBySuccessData(T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }

    public static <T> ServerResponse<T> createBySuccessAll(String msg,T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }

    public static <T> ServerResponse<T> createByError(){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }

    public static <T> ServerResponse<T> createByErrorMsg(String msg){
        return  new ServerResponse<T>(ResponseCode.ERROR.getCode(),msg);
    }

    public static <T> ServerResponse<T> createByErrorCodeMsg(int errCode,String errMsg){
        return new ServerResponse<T>(errCode,errMsg);
    }

    public static <T> ServerResponse<T> createByErrorNoLogin(){
        return new ServerResponse<T>(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
    }

    public static <T> ServerResponse<T> createByErrorIllegalArgument(){
        return new ServerResponse<T>(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
    }

    public static <T> ServerResponse<T> createByErrorValidation(){
        return new ServerResponse<T>(ResponseCode.VALIDATION_ERROR.getCode(),ResponseCode.VALIDATION_ERROR.getDesc());
    }

    public static <T> ServerResponse<T> createByErrorToken(){
        return new ServerResponse<T>(ResponseCode.TOKEN_ERROR.getCode(),ResponseCode.TOKEN_ERROR.getDesc());
    }

    public static <T> ServerResponse<T> createByErrorAbnormalOutputInformation(){
        return new ServerResponse<T>(ResponseCode.ABNORMAL_OUTPUT_INFORMATION.getCode(),ResponseCode.ABNORMAL_OUTPUT_INFORMATION.getDesc());
    }
}
