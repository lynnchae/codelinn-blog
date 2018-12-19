package com.codelinn.blog.config;

import com.codelinn.blog.interceptor.IpInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Class Name : org.lynn.springbootstarter.config
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/12/19 22:32
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private IpInterceptor ipInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(ipInterceptor).addPathPatterns("/**");
    }
}
