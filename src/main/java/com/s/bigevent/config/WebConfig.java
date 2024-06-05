package com.s.bigevent.config;

import com.s.bigevent.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor interceptor;

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //excludePathPatterns(params):除了params中的路径不拦截，其他路径都会拦截
        registry.addInterceptor(interceptor).excludePathPatterns("/user/register", "/user/login");
    }
}
