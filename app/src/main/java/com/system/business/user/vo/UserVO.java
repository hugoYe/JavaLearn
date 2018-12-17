package com.system.business.user.vo;

import com.system.business.permission.Permission;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description = "用户对象")
public class UserVO {

    @ApiModelProperty("用户id")
    private Integer id;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("用户真实姓名")
    private String realName;

    @ApiModelProperty("用户权限")
    private Permission permissions;

    @ApiModelProperty("创建日期")
    private String createTime;

    @ApiModelProperty("渠道id")
    private List<String> channelId;

    @ApiModelProperty("渠道名称")
    private List<String> channelName;

    @ApiModelProperty("所属公司")
    private String company;


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

    public Permission getPermissions() {
        return permissions;
    }

    public void setPermissions(Permission permissions) {
        this.permissions = permissions;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
