package com.example.demo.module.tb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author gyf
 * @since 2020-06-24
 */
@ApiModel(value="A15ToDcProgram对象", description="")
public class A15ToDcProgram extends Model<A15ToDcProgram> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "账簿ID")
    private Long accountId;

    @ApiModelProperty(value = "账簿名称")
    private String accountName;

    @ApiModelProperty(value = "资产账簿")
    private String assetsAccount;

    @ApiModelProperty(value = "在建工程编号")
    private String projectsNumber;

    @ApiModelProperty(value = "在建工程名称")
    private String projectsName;

    @ApiModelProperty(value = "机构编码")
    private String institutionCode;

    @ApiModelProperty(value = "机构名称")
    private String institutionName;

    @ApiModelProperty(value = "0 未报废 1已报废")
    private String status;

    @ApiModelProperty(value = "add-新增 update-修改")
    private String updateType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    public String getAssetsAccount() {
        return assetsAccount;
    }

    public void setAssetsAccount(String assetsAccount) {
        this.assetsAccount = assetsAccount;
    }
    public String getProjectsNumber() {
        return projectsNumber;
    }

    public void setProjectsNumber(String projectsNumber) {
        this.projectsNumber = projectsNumber;
    }
    public String getProjectsName() {
        return projectsName;
    }

    public void setProjectsName(String projectsName) {
        this.projectsName = projectsName;
    }
    public String getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }
    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getUpdateType() {
        return updateType;
    }

    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "A15ToDcProgram{" +
            "id=" + id +
            ", accountId=" + accountId +
            ", accountName=" + accountName +
            ", assetsAccount=" + assetsAccount +
            ", projectsNumber=" + projectsNumber +
            ", projectsName=" + projectsName +
            ", institutionCode=" + institutionCode +
            ", institutionName=" + institutionName +
            ", status=" + status +
            ", updateType=" + updateType +
        "}";
    }
}
