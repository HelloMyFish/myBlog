package com.example.demo.config.time;

import com.example.demo.tb.service.IA15ToDcProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author gyf
 * @date 2020/6/24 11:30
 */
//@Configuration
@EnableScheduling
public class SaticScheduleTask {
    @Autowired
    private IA15ToDcProgramService ia15ToDcProgramService;
    //3.添加定时任务
    @Scheduled(cron = "0/30 * * * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
//        AnalysisBaseDataUtil util =  new AnalysisBaseDataUtil();
        try {
            ia15ToDcProgramService.analysis();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
