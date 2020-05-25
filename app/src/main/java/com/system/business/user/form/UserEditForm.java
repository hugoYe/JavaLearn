package com.system.business.user.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "用户编辑表单")
public class UserEditForm {

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("用户真实姓名")
    private String realName;

    @ApiModelProperty("公司名称")
    private String company;

    @ApiModelProperty(value = "原始密码")
    private String currentPassword;

    @ApiModelProperty(value = "新密码")
    private String newPassword;

    @ApiModelProperty(value = "确认密码")
    private String confirmPassword;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}


