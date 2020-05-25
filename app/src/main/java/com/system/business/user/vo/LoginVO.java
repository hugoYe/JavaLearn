package com.system.business.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "用户登录后对象")
public class LoginVO {

    @ApiModelProperty("token信息")
    private String token;

    @ApiModelProperty("账号")
    private String account;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
