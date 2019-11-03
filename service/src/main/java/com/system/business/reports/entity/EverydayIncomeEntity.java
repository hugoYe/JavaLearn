package com.system.business.reports.entity;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

import java.io.Serializable;


@ColumnWidth(20)
public class EverydayIncomeEntity implements Serializable {

    @ExcelProperty("日期")
    private String date;

    @ExcelProperty("客户id")
    private String customerId;

    @ExcelProperty("渠道名称")
    private String channelName;

    @ExcelProperty("pv/新增")
    private Integer pv;

    @ExcelProperty("uv/活跃")
    private Integer uv;

    @ExcelProperty("收入")
    private Double income;

    @ExcelProperty("真实收入")
    private Double realIncome;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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
}
