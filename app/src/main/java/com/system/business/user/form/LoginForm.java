package com.system.business.user.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("用户登录表单")
public class LoginForm {

    @ApiModelProperty("用户名/用户id")
    private String nameOrUserId;

    @ApiModelProperty(value = "登录密码")
    private String password;

    public String getNameOrUserId() {
        return nameOrUserId;
    }

    public void setNameOrUserId(String nameOrUserId) {
        this.nameOrUserId = nameOrUserId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
