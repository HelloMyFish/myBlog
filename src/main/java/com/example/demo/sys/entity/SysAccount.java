package com.example.demo.sys.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author gyf
 * @since 2020-06-21
 */
@ApiModel(value="SysAccount对象", description="")
public class SysAccount extends Model<SysAccount> {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "user_id")
    private Long userId;

    @ApiModelProperty(value = "账户")
    private String userName;

    @ApiModelProperty(value = "加密后的密码")
    private String md5Password;

    @ApiModelProperty(value = "盐值")
    private String salt;

    @ApiModelProperty(value = "今日登陆失败次数")
    private Integer todayLoginFailTimes;

    @ApiModelProperty(value = "上一次登陆IP地址")
    private String lastLoginIp;

    @ApiModelProperty(value = "上一次登陆时间")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty(value = "是否被删除。默认未删除")
    private String isDeleted;

    @ApiModelProperty(value = "创建人user_id")
    private Long createUserId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人user_id")
    private Long updateUserId;

    @ApiModelProperty(value = "更新人时间")
    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getMd5Password() {
        return md5Password;
    }

    public void setMd5Password(String md5Password) {
        this.md5Password = md5Password;
    }
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    public Integer getTodayLoginFailTimes() {
        return todayLoginFailTimes;
    }

    public void setTodayLoginFailTimes(Integer todayLoginFailTimes) {
        this.todayLoginFailTimes = todayLoginFailTimes;
    }
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }
    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }
    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysAccount{" +
            "id=" + id +
            ", userId=" + userId +
            ", userName=" + userName +
            ", md5Password=" + md5Password +
            ", salt=" + salt +
            ", todayLoginFailTimes=" + todayLoginFailTimes +
            ", lastLoginIp=" + lastLoginIp +
            ", lastLoginTime=" + lastLoginTime +
            ", isDeleted=" + isDeleted +
            ", createUserId=" + createUserId +
            ", createTime=" + createTime +
            ", updateUserId=" + updateUserId +
            ", updateTime=" + updateTime +
        "}";
    }
}
