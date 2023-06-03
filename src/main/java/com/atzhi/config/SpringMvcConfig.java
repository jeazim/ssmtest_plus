package com.atzhi.config;

import com.atzhi.tools.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.atzhi.controller")
public class SpringMvcConfig implements WebMvcConfigurer {
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        // 设置文件最大上传大小
        multipartResolver.setMaxUploadSize(5242880);//5MB
        // 设置文件解析的编码
        multipartResolver.setDefaultEncoding("utf-8");
        return multipartResolver;
    }
    //视图解析器
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/");//配置前缀
        resolver.setSuffix("");//配置后缀
        return resolver;
    }
   // 访问静态资源
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    //对主界面后面的内容拦截
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/course/**");
        System.out.println("---------------------------addInterceptors-------------------");
    }

}
