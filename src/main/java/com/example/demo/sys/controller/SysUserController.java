package com.example.demo.sys.controller;


import com.example.demo.model.ResultData;
import com.example.demo.sys.entity.SysUser;
import com.example.demo.sys.service.ISysUserService;
import com.example.demo.util.email.EmailUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.awt.print.PrinterAbortException;
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
    @Autowired
    private EmailUtil emailUtil;

    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserController.class);

    @ApiOperation(value = "查询用户",notes = "",httpMethod = "GET")
    @GetMapping(value = "query-user")
    public ResultData<SysUser> queryUser(@RequestParam(value = "userName") @ApiParam(value = "userName",required = true) String userName){
        LOGGER.info("userName:{}",userName);
        SysUser sysUser = Optional.ofNullable(sysUserService.queryUserByName(userName)).orElse(new SysUser());
        try {
            emailUtil.send("你好","世界");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return ResultData.ofSuccess(sysUser);
    }

    @ApiOperation(value = "更新用户邮箱",notes = "",httpMethod = "POST")
    @GetMapping(value = "modify_email")
    @PostMapping("modify_email")
    public ResultData modifyEmailToRestValidateCode(@RequestParam(value = "userId") @ApiParam(value = "userId",required = true)Long userId,
                                  @RequestParam(value = "email") @ApiParam(value = "email",required = true)String email){
       return sysUserService.modifyEmail(userId,email);
    }

    @ApiOperation(value = "验证用户邮箱",notes = "",httpMethod = "POST")
    @GetMapping(value = "confirm_email")
    @PostMapping("confirm_email")
    public ResultData confirmEmail(@RequestParam(value = "userId") @ApiParam(value = "userId",required = true)Long userId,
                                                    @RequestParam(value = "email") @ApiParam(value = "email",required = true)String email){
        return sysUserService.confirmEmail(userId,email);
    }

    @ApiOperation(value = "发送邮箱验证码",notes = "",httpMethod = "POST")
    @GetMapping(value = "send_email")
    @PostMapping("send_email")
    public ResultData sendEmail(@RequestParam(value = "userId") @ApiParam(value = "userId",required = true)Long userId){
        return sysUserService.sendEmail(userId);
    }
}
