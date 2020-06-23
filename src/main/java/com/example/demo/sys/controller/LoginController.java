package com.example.demo.sys.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.demo.model.RegisterModel;
import com.example.demo.sys.service.ILoginService;
import com.example.demo.util.ip.IpUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author gyf
 * @date 2020/6/20 18:23
 */
@RestController
@Validated
public class LoginController {
    @Autowired
    private ILoginService iLoginService;

    @ApiOperation(value = "登陆",notes = "",httpMethod = "POST")
    @PostMapping(value = "execute-login")
    public String executeLogin(@ApiParam(value = "userName",required = true) @Param("userName")String userName,
                     @ApiParam(value = "password",required = true) @Param("password")String password){
       R r =  iLoginService.login(userName,password);
       return r.getMsg();
    }

    @ApiOperation(value = "注册",notes = "",httpMethod = "POST")
    @PostMapping(value = "register-user")
    public String register(@Validated @Param("registerModel") RegisterModel registerModel, HttpServletRequest request){
        registerModel.setIpAddress(IpUtils.getIp(request));
        R r =  iLoginService.register(registerModel);
        return r.toString();
    }
}
