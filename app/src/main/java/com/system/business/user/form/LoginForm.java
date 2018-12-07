package com.system.business.user.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("用户登录表单")
public class LoginForm {

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty(value = "登录密码")
    private String password;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
