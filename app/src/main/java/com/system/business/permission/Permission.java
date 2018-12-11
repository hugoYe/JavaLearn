package com.system.business.permission;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "用户权限")
public class Permission {

    @ApiModelProperty("用户角色")
    private String role;

    @ApiModelProperty("用户路由权限列表")
    private String[] visit;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String[] getVisit() {
        return visit;
    }

    public void setVisit(String[] visit) {
        this.visit = visit;
    }
}
