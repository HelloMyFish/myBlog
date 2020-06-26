package com.example.demo.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author gyf
 * @date 2020/6/24 17:16
 */
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContextAware;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContextAware = applicationContext;
    }
    public static Object getBean(String name){
        return applicationContextAware.getBean(name);
    }
}
