package com.example.demo.config.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

/**
 * @author gyf
 * @date 2020/6/26 1:09
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    //实现 WebMvcConfigurer 或者 继承WebMvcConfigurationSupport
    private static final Logger logger = LoggerFactory.getLogger(InterceptorConfig.class);
//    @Bean
//    public InterfaceAuthCheckInterceptor getInterfaceAuthCheckInterceptor() {
//        return new InterfaceAuthCheckInterceptor();
//    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.info("=====拦截器注册====");
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/sys/**","/test/**")
                .excludePathPatterns("/sys/login/execute-login",
                        "/sys/login/to_login",
                        "/",
                        "/js/**",
                        "/css/**",
                        "/images/**",
                        "/templates/**");
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/sys/login/to_login").setViewName("login");
        registry.addViewController("/").setViewName("index");
    }
    /**
     * 配置静态访问资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //静态资源映射
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");

        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
//                .addResourceLocations("file:"+url+"/");
        registry.addResourceHandler("/article/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/categories/**")
                .addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("/templates/**")
//                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/time/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/es/**")
                .addResourceLocations("classpath:/static/");
    }
    /**
     * 配置请求视图映射
     * @return
     */
    @Bean
    public InternalResourceViewResolver resourceViewResolver()
    {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        //请求视图文件的前缀地址
//        internalResourceViewResolver.setPrefix("/WEB-INF/jsp/");
        internalResourceViewResolver.setPrefix("/templates/");
        //请求视图文件的后缀
        internalResourceViewResolver.setSuffix(".html");
        return internalResourceViewResolver;
    }


//    /**
//     * 消息内容转换配置
//     * 配置fastJson返回json转换
//     * @param converters
//     */
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        //调用父类的配置
//        super.configureMessageConverters(converters);
//        //创建fastJson消息转换器
//        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//        //创建配置类
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        //修改配置返回内容的过滤
//        fastJsonConfig.setSerializerFeatures(
//                SerializerFeature.DisableCircularReferenceDetect,
//                SerializerFeature.WriteMapNullValue,
//                SerializerFeature.WriteNullStringAsEmpty
//        );
//        fastConverter.setFastJsonConfig(fastJsonConfig);
//        //将fastjson添加到视图消息转换器列表内
//        converters.add(fastConverter);
//    }
}
