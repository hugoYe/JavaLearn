package com.system.business.adv.offermanager.dto;

import com.system.common.dto.PageQueryDTO;

public class OfferManagerQueryDto extends PageQueryDTO {

    /**
     * 自有系统自定义的offerID
     */
    private String offerId;

    /**
     * 广告主提供的offerId
     */
    private String advOfferId;

    /**
     * 广告主名称+广告主ID
     */
    private String advertiser;


    /**
     * offer名称
     */
    private String offerName;


    /**
     * offer广告类型
     */
    private String offerType;


    /**
     * offer对应国家
     */
    private String country;

    /**
     * offer对应运营商
     */
    private String carrier;

    /**
     * 状态on/off: 1.on，0.off
     */
    private Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
