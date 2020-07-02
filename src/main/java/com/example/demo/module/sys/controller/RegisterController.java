package com.example.demo.module.sys.controller;

import com.example.demo.model.RegisterModel;
import com.example.demo.model.ResultData;
import com.example.demo.module.sys.service.IRegisterService;
import com.example.demo.util.ip.IpUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author gyf
 * @date 2020/6/25 23:41
 */
@RestController(value = "/sys/register")
@Validated
public class RegisterController {
    @Autowired
    private IRegisterService iRegisterService;
    @ApiOperation(value = "注册",notes = "",httpMethod = "POST")
    @PostMapping(value = "register-user")
    public ResultData register(@Validated @Param("registerModel") RegisterModel registerModel, HttpServletRequest request){
        registerModel.setIpAddress(IpUtils.getIp(request));
        IpUtils.getIpAddr(request);
        return iRegisterService.register(registerModel);
    }
}
