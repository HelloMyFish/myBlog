package com.example.demo.module.sys.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author gyf
 * @date 2020/6/25 14:07
 */
@ApiModel(value="登陆入参对象", description="")
public class LoginInputModel {

    @ApiModelProperty(value = "账户",required = true)
    private String userName;
    @ApiModelProperty(value = "密码",required = true)
    private String password;
    @ApiModelProperty(value = "IP地址")
    private String ipAddress;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
