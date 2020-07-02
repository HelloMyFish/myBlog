package com.example.demo.modul.tb.util;

import com.example.demo.config.SpringContextUtil;
import com.example.demo.modul.tb.service.IA15ToDcProgramService;

import java.io.IOException;

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
