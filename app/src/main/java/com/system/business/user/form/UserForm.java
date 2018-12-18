package com.system.business.user.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("用户信息表单")
public class UserForm {

    @ApiModelProperty("用户id")
    private Integer id;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("用户真实姓名")
    private String realName;

    @ApiModelProperty("公司名称")
    private String company;

    @ApiModelProperty("渠道id")
    private List<String> channelId;

    @ApiModelProperty("渠道名称")
    private List<String> channelName;

    public String getUserName() {
        return userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public List<String> getChannelId() {
        return channelId;
    }

    public void setChannelId(List<String> channelId) {
        this.channelId = channelId;
    }

    public List<String> getChannelName() {
        return channelName;
    }

    public void setChannelName(List<String> channelName) {
        this.channelName = channelName;
    }
}
