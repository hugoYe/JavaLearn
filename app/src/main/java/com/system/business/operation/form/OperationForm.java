package com.system.business.operation.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class OperationForm {

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("渠道id")
    private String channelId;

    @ApiModelProperty("日期")
    private String date;

    @ApiModelProperty("pv")
    private Integer pv;

    @ApiModelProperty("uv")
    private Integer uv;

    @ApiModelProperty("收入")
    private Double income;

    @ApiModelProperty("真实收入")
    private Double realIncome;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}
