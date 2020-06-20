package com.example.demo.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.demo.model.RegisterModel;
import com.example.demo.model.ResultData;
import com.example.demo.service.LoginService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gyf
 * @date 2020/6/20 18:23
 */
@RestController
@Validated
public class LoginController {
    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "登陆",notes = "",httpMethod = "POST")
    @PostMapping(value = "executeLogin")
    public String executeLogin(@ApiParam(value = "userName",required = true) @Param("userName")String userName,
                     @ApiParam(value = "password",required = true) @Param("password")String password){
       R r =  loginService.login(userName,password);
       return r.getMsg();
    }

    @ApiOperation(value = "注册",notes = "",httpMethod = "POST")
    @PostMapping(value = "register")
    public String register(@Validated @Param("userName") RegisterModel registerModel){
        R r =  loginService.register(registerModel);
        return r.toString();
    }
}
