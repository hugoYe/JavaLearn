package com.system.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description = "批量用户对象")
public class UsersVO {

    @ApiModelProperty("用户总数")
    private Integer total;

    @ApiModelProperty("用户列表")
    private List<UserVO> list;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<UserVO> getList() {
        return list;
    }

    public void setList(List<UserVO> list) {
        this.list = list;
    }
}
