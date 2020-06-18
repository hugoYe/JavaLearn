package com.system.business.adv.apioffer.vo;


import io.swagger.annotations.ApiModelProperty;

public class ApiOfferVO {


    /**
     * 广告主提供的offerId
     */
    @ApiModelProperty("广告主提供的offerId")
    private String advOfferId;

    /**
     * 广告主名称+广告主ID
     */
    @ApiModelProperty("广告主名称+广告主ID")
    private String advertiser;

    /**
     * offer名称
     */
    @ApiModelProperty("offer名称")
    private String offerName;

    /**
     * offer广告类型
     */
    @ApiModelProperty("offer广告类型")
    private String offerType;

    /**
     * 类型分类: 2.非成人，1.成人
     */
    @ApiModelProperty("类型分类: 2.非成人，1.成人")
    private Integer category;

    /**
     * offer对应国家
     */
    @ApiModelProperty("offer对应国家")
    private String country;

    /**
     * offer对应运营商
     */
    @ApiModelProperty("offer对应运营商")
    private String carrier;

    /**
     * offer链接
     */
    @ApiModelProperty("offer链接")
    private String offerUrl;

    /**
     * 落地页预览链接
     */
    @ApiModelProperty("落地页预览链接")
    private String previewUrl;

    /**
     * 客户回调连接
     */
    @ApiModelProperty("客户回调连接")
    private String postbackUrl;

    /**
     * 收益比例
     */
    @ApiModelProperty("收益比例")
    private Integer proportion;

    /**
     * offer接入价格
     */
    @ApiModelProperty("offer接入价格")
    private Double payoutIn;

    /**
     * offer外放价格
     */
    @ApiModelProperty("offer外放价格")
    private Double payoutOut;

    /**
     * 币种
     */
    @ApiModelProperty("币种")
    private String currencyUnit;

    /**
     * 1click/2click/pin/sms/captcha/1click+pin/1click+captcha
     */
    @ApiModelProperty("1click/2click/pin/sms/captcha/1click+pin/1click+captcha")
    private String process;

    /**
     * 单日offer上限值
     */
    @ApiModelProperty("单日offer上限值")
    private Integer cap;

    /**
     * 时区，需要支持选择不同时区
     */
    @ApiModelProperty("时区，需要支持选择不同时区")
    private String timeZone;

    /**
     * 备注，支持人工填写，没有默认为空
     */
    @ApiModelProperty("备注，支持人工填写，没有默认为空")
    private String description;

    /**
     * 状态on/off: 1.on，0.off
     */
    @ApiModelProperty("状态on/off: 1.on，0.off")
    private Integer status;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private String createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private String updateTime;

    public String getAdvOfferId() {
        return advOfferId;
    }

    public void setAdvOfferId(String advOfferId) {
        this.advOfferId = advOfferId;
    }

    public String getAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(String advertiser) {
        this.advertiser = advertiser;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getOfferUrl() {
        return offerUrl;
    }

    public void setOfferUrl(String offerUrl) {
        this.offerUrl = offerUrl;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getPostbackUrl() {
        return postbackUrl;
    }

    public void setPostbackUrl(String postbackUrl) {
        this.postbackUrl = postbackUrl;
    }

    public Integer getProportion() {
        return proportion;
    }

    public void setProportion(Integer proportion) {
        this.proportion = proportion;
    }

    public Double getPayoutIn() {
        return payoutIn;
    }

    public void setPayoutIn(Double payoutIn) {
        this.payoutIn = payoutIn;
    }

    public Double getPayoutOut() {
        return payoutOut;
    }

    public void setPayoutOut(Double payoutOut) {
        this.payoutOut = payoutOut;
    }

    public String getCurrencyUnit() {
        return currencyUnit;
    }

    public void setCurrencyUnit(String currencyUnit) {
        this.currencyUnit = currencyUnit;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public Integer getCap() {
        return cap;
    }

    public void setCap(Integer cap) {
        this.cap = cap;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
