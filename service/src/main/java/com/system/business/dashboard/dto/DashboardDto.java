package com.system.business.dashboard.dto;

import java.util.Date;

public class DashboardDto {
    /**
     * 页面访问量
     */
    private Integer pv;

    /**
     * 独立访客访问数
     */
    private Integer uv;

    /**
     * 收入
     */
    private Double income;

    /**
     * 真实收入
     */
    private Double realIncome;

    /**
     * 日期
     */
    private Date date;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 渠道id
     */
    private String channelId;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}
