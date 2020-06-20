package com.example.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


/**
 * @author gyf
 * @date 2020/6/20 22:29
 */
@ApiModel(value = "注册功能入参实体类", description="注册功能入参实体类")
public class RegisterModel {
    @Size(min = 6,max = 12)
    @ApiModelProperty(value = "用户名",required = true)
    private String userName;

    @Size(min = 2,max = 20)
    @ApiModelProperty(value = "真实姓名",required = true)
    private String trueName;

    @Size(min = 6,max = 12)
    @ApiModelProperty(value = "密码",required = true)
    private String password;

    @Pattern(regexp="^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\\\d{8}$",message = "手机号不正确")
    @ApiModelProperty(value = "注册手机号")
    private String mobile;

    @Pattern(regexp="^\\w+([-+.]\\w+)@\\w+([-.]\\w+).\\w+([-.]\\w+)$",message = "邮箱不正确")
    @ApiModelProperty(value = "邮箱")
    private String email;

    @Pattern(regexp = "^1050|1100|2100$")
    @ApiModelProperty(value = "性别",allowableValues="1050,1100,2100")
    private String sex;

    @Size(max = 50,message = "最大长度不允许超过50")
    @ApiModelProperty(value = "家庭住址")
    private String location;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    @Override
    public String toString() {
        return "RegisterModel{" +
                "userName='" + userName + '\'' +
                ", trueName='" + trueName + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
