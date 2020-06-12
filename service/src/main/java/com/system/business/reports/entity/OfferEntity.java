package com.system.business.reports.entity;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

import java.io.Serializable;


@ColumnWidth(20)
public class OfferEntity implements Serializable {

    @ExcelProperty("offerID")
    private String offerId;

    @ExcelProperty("广告主offerID")
    private String advOfferId;

    @ExcelProperty("广告主")
    private String advertiser;

    @ExcelProperty("offer名称")
    private String offerName;

    @ExcelProperty("广告类型")
    private String offerType;

    @ExcelProperty("国家")
    private String country;

    @ExcelProperty("运营商")
    private String carrier;

    @ExcelProperty("offer接入价格")
    private Double payoutIn;

    @ExcelProperty("offer外放价格")
    private Double payoutOut;

    @ExcelProperty("process")
    private String process;

    @ExcelProperty("单日offer上限值")
    private Integer cap;

    @ExcelProperty("时区")
    private String timeZone;

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
}
