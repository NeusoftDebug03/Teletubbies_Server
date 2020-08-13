package com.debug.teletubbies.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class MyMVCConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //指向外部目录
        //registry.addResourceHandler("imgs//**").addResourceLocations("file:/home/imgs/");
        registry.addResourceHandler("imgs//**").addResourceLocations("file:D:/img/");
        super.addResourceHandlers(registry);
    }
}