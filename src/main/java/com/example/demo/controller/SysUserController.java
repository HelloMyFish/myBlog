package com.example.demo.controller;

import com.example.demo.entity.SysUser;
import com.example.demo.service.SysUserService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

/**
 * @Title:
 * @Package
 * @Description:
 * @Author gyf
 * @Date 2020/6/20 12:56
 */
@RestController
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserController.class);
    @ApiOperation(value = "查询用户",notes = "",httpMethod = "GET")
    @GetMapping(value = "queryUser")
    public String queryUser(@RequestParam(value = "userName") @ApiParam(value = "userName",required = true) String userName){
        LOGGER.info("userName:{}",userName);
        return Optional.ofNullable(sysUserService.queryUserByName(userName)).orElse(new SysUser()).toString();
    }
}
