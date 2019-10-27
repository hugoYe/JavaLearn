package com.system.business.reports.entity;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

import java.io.Serializable;
import java.util.Date;


@ColumnWidth(20)
public class EverydayIncomeEntity implements Serializable {

    @ExcelProperty("日期")
    private Date date;

    @ExcelProperty("客户id")
    private String userId;

    @ExcelProperty("渠道名称")
    private String channleName;

    @ExcelProperty("pv/新增")
    private Integer pv;

    @ExcelProperty("uv/活跃")
    private Integer uv;

    @ExcelProperty("收入")
    private Double income;

    @ExcelProperty("真实收入")
    private Double realIncome;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChannleName() {
        return channleName;
    }

    public void setChannleName(String channleName) {
        this.channleName = channleName;
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
