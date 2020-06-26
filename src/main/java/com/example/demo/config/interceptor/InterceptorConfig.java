package com.example.demo.config.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author gyf
 * @date 2020/6/26 1:09
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(InterceptorConfig.class);
//    @Bean
//    public InterfaceAuthCheckInterceptor getInterfaceAuthCheckInterceptor() {
//        return new InterfaceAuthCheckInterceptor();
//    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.info("=====拦截器注册====");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/sys/**","/test/**");
        super.addInterceptors(registry);
    }
}
