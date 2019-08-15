package com.xtang;

import com.xtang.controller.interceptor.MiniInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @program: short-video-back
 * @Date: 2019/7/8 23:52
 * @Author: xTang
 * @Description:
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/")
                .addResourceLocations("file:G:/short-video-back-dev/");
    }

    @Bean
    public MiniInterceptor miniInterceptor() {
        return new MiniInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(miniInterceptor()).addPathPatterns("/userService/**")
                .addPathPatterns("/video/uploadVideo")
                .addPathPatterns("/video/userLike","/video/userUnlike","/video/saveComment")
                .addPathPatterns("/bgm/**");
        super.addInterceptors(registry);
    }
}
