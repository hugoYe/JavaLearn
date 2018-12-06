package com.system.user.vo;

import com.system.permission.Permission;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "用户对象")
public class UserVO {

    @ApiModelProperty("用户id")
    private Integer id;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("用户真实姓名")
    private String realName;

    @ApiModelProperty("用户权限")
    private Permission permissions;

    @ApiModelProperty("创建日期")
    private String createTime;

    @ApiModelProperty("渠道id")
    private String channelId;

    @ApiModelProperty("渠道名称")
    private String channelName;

    @ApiModelProperty("所属公司")
    private String company;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
