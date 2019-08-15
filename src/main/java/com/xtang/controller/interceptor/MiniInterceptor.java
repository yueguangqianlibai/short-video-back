package com.xtang.controller.interceptor;

import com.xtang.common.ServerResponse;
import com.xtang.utils.JsonUtils;
import com.xtang.utils.RedisOperator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * @program: short-video-back
 * @Date: 2019/7/22 3:27
 * @Author: xTang
 * @Description:
 */
public class MiniInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisOperator redis;
    public static final String USERS_REDIS_SESSION = "users-redis-session";

    /**
     * 拦截请求，在controller调用之前拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String userId = request.getHeader("userId");
        String userToken = request.getHeader("userToken");

        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(userToken)) {
            String uniqueToken = redis.get(USERS_REDIS_SESSION + ":" + userId);
            if (StringUtils.isEmpty(uniqueToken) && StringUtils.isBlank(uniqueToken)) {
                System.out.println("请登录...");
                returnErrorResponse(response, new ServerResponse(50,"请登录...").createByErrorToken("请登录..."));
                return false;
            } else {
                if (!uniqueToken.equals(userToken)) {
                    System.out.println("账号被挤出...");
                    returnErrorResponse(response, new ServerResponse(50,"请登录...").createByErrorToken("账号被挤出..."));
                    return false;
                }
            }
        } else {
            System.out.println("请登录...");
            returnErrorResponse(response, new ServerResponse(50,"请登录...").createByErrorToken("请登录..."));
            return false;
        }
        return true;
    }

    public void returnErrorResponse(HttpServletResponse response, ServerResponse result)
            throws IOException, UnsupportedEncodingException {
        OutputStream out = null;
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json");
            out = response.getOutputStream();
            out.write(JsonUtils.objectToJson(result).getBytes("utf-8"));
            out.flush();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 请求controller之后，渲染视图之前进行拦截
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 请求controller之后，渲染视图之后进行拦截
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
