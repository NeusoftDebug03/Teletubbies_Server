package com.debug.teletubbies.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.google.common.base.Predicates;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

import java.util.function.Predicate;

@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host("")
                .apiInfo(apiInfo())// 不配置，默认当前项目的端口
                .pathMapping("/")
                .select()// 选择那些路径和API会生成Document

                // 对所有的API进行监控
                // .apis(RequestHandlerSelectors.any())

                // 为当前包下controller生成API文档
                .apis(RequestHandlerSelectors.basePackage("com.debug.teletubbies.controller"))// 选择监控的Package

                // 为有@Api注解的Controller生成API文档
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))

                //为有@ApiOperation注解的方法生成API文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))

                // 不显示错误的接口地址
                .paths(Predicates.not(PathSelectors.regex("/erroe.*")))// 错误路径不监控
                .paths(PathSelectors.any())// 对根下所有路径进行监控
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Teletubbies母婴商城项目接口文档")
                .contact(new Contact("Teletubbies", "www.teletubbies.com","yizhaosan.@gmail.com"))
                .description("Swagger-UI生成的接口文档")
                .version("0.0.1")
                .build();
    }
}
