package com.system.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "用户对象")
public class UserVO {

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("用户真实姓名")
    private String realName;

    @ApiModelProperty("是否管理员")
    private Integer isRoot;

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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getIsRoot() {
        return isRoot;
    }

    public void setIsRoot(Integer isRoot) {
        this.isRoot = isRoot;
    }
}
