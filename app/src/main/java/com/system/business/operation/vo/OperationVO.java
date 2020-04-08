package com.system.business.operation.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "收入实体对象")
public class OperationVO {

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("日期")
    private String date;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("客户id")
    private String customerId;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("用户真名")
    private String realName;

    @ApiModelProperty("渠道id")
    private String channelId;

    @ApiModelProperty("渠道名称")
    private String channelName;

    @ApiModelProperty("pv")
    private Integer pv;

    @ApiModelProperty("uv")
    private Integer uv;

    @ApiModelProperty("收入")
    private Double income;

    @ApiModelProperty("真实收入")
    private Double realIncome;

    @ApiModelProperty("收益比例")
    private Integer incomeRate;

    @ApiModelProperty("币种")
    private String currency;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public Integer getPv() {
        return pv;
    }

    public void setPv(Integer pv) {
        this.pv = pv;
    }

    public Integer getUv() {
        return uv;
    }

    public void setUv(Integer uv) {
        this.uv = uv;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getRealIncome() {
        return realIncome;
    }

    public void setRealIncome(Double realIncome) {
        this.realIncome = realIncome;
    }

    public Integer getIncomeRate() {
        return incomeRate;
    }

    public void setIncomeRate(Integer incomeRate) {
        this.incomeRate = incomeRate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
