package com.system.business.user.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("添加用户表单")
public class UserAddForm {

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("用户真实姓名")
    private String realName;

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
}
