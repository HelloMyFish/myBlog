package com.example.demo.sys.controller;

import com.example.demo.model.ResultData;
import com.example.demo.sys.model.LoginInputModel;
import com.example.demo.sys.service.ILoginService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author gyf
 * @date 2020/6/20 18:23
 */
@Controller
@RequestMapping(value = "/sys/login")
@Validated
public class LoginController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private ILoginService iLoginService;
    @ResponseBody
    @ApiOperation(value = "登陆",notes = "",httpMethod = "POST")
    @PostMapping(value = "execute-login")
    public ResultData executeLogin(@RequestBody LoginInputModel model, HttpServletRequest request){
       return iLoginService.login(model,request);
    }

//    @ApiOperation(value = "跳转到login页面",notes = "",httpMethod = "GET")
//    @GetMapping(value = "to_login")
//    public String toLogin(){
//        return "login";
//    }

}
