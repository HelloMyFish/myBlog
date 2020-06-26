package com.example.demo.tb.util;

import com.example.demo.config.SpringContextUtil;
import com.example.demo.tb.entity.A15ToDcProgram;
import com.example.demo.tb.service.IA15ToDcProgramService;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;

/**
 * @author gyf
 * @date 2020/6/24 16:35
 */
public class AnalysisBaseDataUtil {

    public void analysis() throws IOException {
        IA15ToDcProgramService ia15ToDcProgramService = (IA15ToDcProgramService)SpringContextUtil.getBean("IA15ToDcProgramService");
//        AnnotationConfigApplicationContext annotationConfigApplicationContext =
//                new AnnotationConfigApplicationContext();
//        annotationConfigApplicationContext.refresh();
//        IA15ToDcProgramService ia15ToDcProgramService = (IA15ToDcProgramService)annotationConfigApplicationContext.getBean("A15ToDcProgramServiceImpl");
        ia15ToDcProgramService.analysis();
    }
}
