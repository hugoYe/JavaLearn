package com.system.business.reports.dto;


public class UploadExcelDto {
    /**
     * 日期
     */
    private String date;

    /**
     * 客户id
     */
    private String customerId;

    /**
     * pv/新增
     */
    private Integer pv;

    /**
     * uv/活跃
     */
    private Integer uv;

    /**
     * 真实收入
     */
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

    public Double getRealIncome() {
        return realIncome;
    }

    public void setRealIncome(Double realIncome) {
        this.realIncome = realIncome;
    }
}
