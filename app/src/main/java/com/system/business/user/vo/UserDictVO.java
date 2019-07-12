package com.system.business.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "用户字典对象")
public class UserDictVO {

    @ApiModelProperty("用户id")
    private Integer id;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("用户真实姓名")
    private String realName;

    @ApiModelProperty("收益比例")
    private Integer incomeRate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getIncomeRate() {
        return incomeRate;
    }

    public void setIncomeRate(Integer incomeRate) {
        this.incomeRate = incomeRate;
    }
}
