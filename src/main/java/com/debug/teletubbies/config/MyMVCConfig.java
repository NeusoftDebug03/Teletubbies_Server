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
//        registry.addResourceHandler("imgs//**")
//                .addResourceLocations("file:D:/img/");

        // 获取项目路径
        // 使用string.replaceAll("\\\\","/")将反斜杠替换为斜杠
        // System.out.println(System.getProperty("user.dir"));
        String URL1 = System.getProperty("user.dir");

        String URL2 = "\\src\\main\\resources\\static";

        String URL3 = "\\imgs";

        // System.out.println("\n" + (URL1 + URL2 + URL3).replaceAll("\\\\", "/") + "\n");

        // registry.addResourceHandler("img//**")
        //      .addResourceLocations("file:D:/img/");
        registry.addResourceHandler((URL1 + URL2 + URL3).replaceAll("\\\\", "/") + "//**")
                .addResourceLocations("file:" + (URL1 + URL2 + URL3).replaceAll("\\\\", "/") + "/");

        super.addResourceHandlers(registry);
    }
}