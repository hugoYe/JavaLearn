package com.system.business.adv.offermanager.domain;

import com.system.common.domain.BaseDomain;

import javax.persistence.*;

/**
 * offer管理表
 */
@Entity
@Table(name = "t_adv_offer_management")
public class OfferManagerDomain extends BaseDomain {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    /**
     * 自有系统自定义的offerID
     */
    @Column(name = "offer_id")
    private String offerId;

    /**
     * 广告主提供的offerId
     */
    @Column(name = "adv_offer_id")
    private String advOfferId;

    /**
     * 广告主名称+广告主ID
     */
    @Column(name = "advertiser")
    private String advertiser;

    /**
     * offer名称
     */
    @Column(name = "offer_name")
    private String offerName;

    /**
     * offer广告类型
     */
    @Column(name = "offer_type")
    private String offerType;

    /**
     * 类型分类: 2.非成人，1.成人
     */
    @Column(name = "category")
    private Integer category;

    /**
     * offer对应国家
     */
    @Column(name = "country")
    private String country;

    /**
     * offer对应运营商
     */
    @Column(name = "carrier")
    private String carrier;

    /**
     * offer链接
     */
    @Column(name = "offer_url")
    private String offerUrl;

    /**
     * 落地页预览链接
     */
    @Column(name = "preview_url")
    private String previewUrl;

    /**
     * 客户回调连接
     */
    @Column(name = "postback_url")
    private String postbackUrl;

    /**
     * 收益比例
     */
    @Column(name = "proportion")
    private Integer proportion;

    /**
     * offer接入价格
     */
    @Column(name = "payout_in")
    private Double payoutIn;

    /**
     * offer外放价格
     */
    @Column(name = "payout_out")
    private Double payoutOut;

    /**
     * 币种
     */
    @Column(name = "currency_unit")
    private String currencyUnit;

    /**
     * 1click/2click/pin/sms/captcha/1click+pin/1click+captcha
     */
    @Column(name = "process")
    private String process;

    /**
     * 单日offer上限值
     */
    @Column(name = "cap")
    private Integer cap;

    /**
     * 时区，需要支持选择不同时区
     */
    @Column(name = "time_zone")
    private String timeZone;

    /**
     * 备注，支持人工填写，没有默认为空
     */
    @Column(name = "description")
    private String description;

    /**
     * 状态on/off: 1.on，0.off
     */
    @Column(name = "status")
    private Integer status = 1;

    /**
     * 被删除的数据: 1.已删除，0.未删除
     */
    @Column(name = "is_deleted")
    private Integer isDeleted = 0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
