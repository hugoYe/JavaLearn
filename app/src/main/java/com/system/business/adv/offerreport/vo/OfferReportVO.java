package com.system.business.adv.offerreport.vo;


import io.swagger.annotations.ApiModelProperty;

public class OfferReportVO {


    /**
     * 日期
     */
    @ApiModelProperty("日期")
    private String date;

    /**
     * 用户总访问次数（同一用户24小时内多次访问计算多次，按北京时间计算）
     */
    @ApiModelProperty("用户总访问次数")
    private String grossClicks;

    /**
     * 独立用户总访问次数（同一用户24小时内多次访问只计算一次，按北京时间计算）
     */
    @ApiModelProperty("独立用户总访问次数")
    private String uniqueClicks;

    /**
     * 订阅成功数（广告主回传给我们）
     */
    @ApiModelProperty("订阅成功数")
    private String conversions;

    /**
     * 显示总收益。（公式为：conversions*price。即订阅成功数*单条offer价格）
     */
    @ApiModelProperty("显示总收益")
    private Integer revenue;

    /**
     * 显示转化率。（公式为：conversions/unique clicks。即订阅成功数/独立访问数）
     */
    @ApiModelProperty("显示转化率")
    private String cr;

    /**
     * 平均点击价格。（公式为：revenue/unique clicks。即收益/独立访问数）
     */
    @ApiModelProperty("平均点击价格")
    private String ecpc;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGrossClicks() {
        return grossClicks;
    }

    public void setGrossClicks(String grossClicks) {
        this.grossClicks = grossClicks;
    }

    public String getUniqueClicks() {
        return uniqueClicks;
    }

    public void setUniqueClicks(String uniqueClicks) {
        this.uniqueClicks = uniqueClicks;
    }

    public String getConversions() {
        return conversions;
    }

    public void setConversions(String conversions) {
        this.conversions = conversions;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    public String getCr() {
        return cr;
    }

    public void setCr(String cr) {
        this.cr = cr;
    }

    public String getEcpc() {
        return ecpc;
    }

    public void setEcpc(String ecpc) {
        this.ecpc = ecpc;
    }
}
