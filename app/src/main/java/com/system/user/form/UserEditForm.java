package com.system.user.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("编辑用户表单")
public class UserEditForm {

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("用户真实姓名")
    private String realName;

    @ApiModelProperty(value = "登录密码")
    private String password;

    @ApiModelProperty(value = "确认密码")
    private String confirmPassword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
