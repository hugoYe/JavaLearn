package com.system.business.user.dto;

import java.util.List;

public class UserDTO {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户真实名称
     */
    private String realName;

    /**
     * 是否超管 1是 0否
     */
    private Integer isRoot;

    /**
     * 被删除的数据: 1.已删除，0.未删除
     */
    private Integer isDeleted;

    /**
     * 创建日期
     */
    private String createTime;

    /**
     * 渠道id
     */
    private String channelId;

    /**
     * 渠道名称
     */
    private List<String> channelName;

    /**
     * 所属公司
     */
    private String company;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
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

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", isRoot=" + isRoot +
                ", isDeleted=" + isDeleted +
                ", createTime='" + createTime + '\'' +
                ", channelId='" + channelId + '\'' +
                ", channelName='" + channelName + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
