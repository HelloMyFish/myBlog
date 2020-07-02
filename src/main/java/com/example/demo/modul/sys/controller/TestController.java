package com.example.demo.modul.sys.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author gyf
 * @date 2020/6/21 16:22
 */
@RestController(value = "test")
public class TestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @ApiOperation(value = "日期测试接口",notes = "",httpMethod = "GET")
    @GetMapping(value = "test")
    public String test(@RequestParam("dateTime") LocalDateTime dateTime,
                       @RequestParam("string") String str,
                       @RequestParam("string2List") List list){
        System.out.println();
        LOGGER.info("DATE_TIME_FORMATTER:{}",DATE_TIME_FORMATTER.format(dateTime) );
        LOGGER.info("str:{}", str);
        LOGGER.info("list:{}", list.toString());
        return DATE_TIME_FORMATTER.format(dateTime);
    }
}
