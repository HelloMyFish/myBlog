package com.example.demo.tb.service;

import com.example.demo.tb.entity.A15ToDcProgram;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gyf
 * @since 2020-06-24
 */
public interface IA15ToDcProgramService extends IService<A15ToDcProgram> {

    void analysis() throws IOException;
}
