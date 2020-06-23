package com.example.demo.sys.controller;


import com.example.demo.sys.entity.SysUser;
import com.example.demo.sys.service.ISysUserService;
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
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gyf
 * @since 2020-06-21
 */
@RestController
@RequestMapping("/sys/sys-user")
public class SysUserController {
    @Autowired
    private ISysUserService sysUserService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserController.class);

    @ApiOperation(value = "查询用户",notes = "",httpMethod = "GET")
    @GetMapping(value = "query-user")
    public String queryUser(@RequestParam(value = "userName") @ApiParam(value = "userName",required = true) String userName){
        LOGGER.info("userName:{}",userName);
        return Optional.ofNullable(sysUserService.queryUserByName(userName)).orElse(new SysUser()).toString();
    }

}
