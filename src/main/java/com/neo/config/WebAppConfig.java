package com.neo.config;

/**
 * Created by liudong on 2018/6/8.
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.util.List;

/**
 * 注册拦截器
 * Created by SYSTEM on 2017/8/16.
 */
@Configuration
//@EnableWebMvc
public class WebAppConfig extends WebMvcConfigurerAdapter {


//    // 如果启用了 @EnableWebMvc 注解 必须在这里设置 静态资源路径 properties中的设置将失效
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        //将所有/static/** 访问都映射到classpath:/static/  目录下  favicon.ico 太坑爹了。。。
//        registry.addResourceHandler("/static/**","/favicon.ico")
//                .addResourceLocations("classpath:/static/", "file:/Users/liudong/Downloads/111111111/");
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(new InterceptorConfig())
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login", "/user/register","/");
    }

    // 以下 两个方法 解决乱码问题
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        HttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(responseBodyConverter());
    }

}