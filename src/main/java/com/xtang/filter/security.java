package com.xtang.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @program: short-video-back
 * @Date: 2019/7/6 14:04
 * @Author: xTang
 * @Description: 拦截器
 */
@WebFilter(filterName = "myFilter", urlPatterns = {"/test/*"})
public class security implements Filter {

    private Logger logger = LoggerFactory.getLogger(security.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("==========================================进入过滤器==========================================");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if ("true".equals(session.getAttribute("isLogin").toString())) {
            if (session.getAttribute("Token") != null){
                System.err.println("获取到的token是："+session.getAttribute("Token"));
                filterChain.doFilter(request, response);
                return;
            }
        }
        String fatherUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        response.sendRedirect(fatherUrl + "/users/test");
        logger.info("==========================================过滤器结束==========================================");
    }
}
