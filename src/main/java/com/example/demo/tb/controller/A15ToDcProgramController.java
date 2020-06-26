package com.example.demo.tb.controller;


import com.example.demo.tb.service.IA15ToDcProgramService;
import com.example.demo.tb.service.impl.A15ToDcProgramServiceImpl;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gyf
 * @since 2020-06-24
 */
@RestController
@RequestMapping("/tb/a15-to-dc-program")
public class A15ToDcProgramController {
    @Autowired
    public IA15ToDcProgramService a15ToDcProgramService;

    @GetMapping("handle_base_data")
    public String handleBaseData() throws IOException {
        a15ToDcProgramService.analysis();
        return "success";
    }

}
