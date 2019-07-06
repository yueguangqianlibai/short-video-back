package com.xtang;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: short-video-back
 * @Date: 2019/7/5 22:08
 * @Author: xTang
 * @Description:
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    /**
     * swagger2的配置文件，这里可以配置swagger2的一些的基本内容，比如扫描的包等等
     */
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.xtang.controller"))
                .paths(PathSelectors.any()).build();
    }

    /**
     * 构建api文档的信息
     */
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                //设置标题
                .title("使用swagger2构建短视频后台api接口文档")
                //设置联系人
                .contact(new Contact("xTang","https://www.github.com/yueguangqianlibai","cooldude008@163.com"))
                //响应描述
                .description("欢迎访问小唐短视频接口文档,shortVideo 提供后端接口")
                //设置版本
                .version("v1.0.1").build();
    }
}
