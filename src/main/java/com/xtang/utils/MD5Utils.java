package com.xtang.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import java.security.MessageDigest;

/**
 * @program: shortvideo
 * @Date: 2019/6/25 16:06
 * @Author: xTang
 * @Description:
 */
public class MD5Utils {

    /**
    * @Description: 对字符串进行md5加密
    */
    public static String getMD5Str(String strValue) throws Exception{
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        String newstr = Base64.encodeBase64String(md5.digest(strValue.getBytes()));
        return newstr;
    }

    public static void main(String[] args){
        try{
            String md5 = getMD5Str("xiaotang");
            System.out.println(md5);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
