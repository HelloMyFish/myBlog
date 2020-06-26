package com.example.demo.sys.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.demo.model.RegisterModel;
import com.example.demo.model.ResultData;
import com.example.demo.sys.model.LoginInputModel;
import com.example.demo.sys.service.ILoginService;
import com.example.demo.util.ip.IpUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author gyf
 * @date 2020/6/20 18:23
 */
@RestController(value = "/sys/login")
@Validated
public class LoginController {
    @Autowired
    private ILoginService iLoginService;

    @ApiOperation(value = "登陆",notes = "",httpMethod = "POST")
    @PostMapping(value = "execute-login")
    public ResultData executeLogin(@RequestBody LoginInputModel model, HttpServletRequest request){
       return iLoginService.login(model,request);
    }


}
