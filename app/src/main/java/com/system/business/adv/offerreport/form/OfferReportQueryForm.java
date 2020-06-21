package com.system.business.adv.offerreport.form;

import com.system.common.form.PageForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel
public class OfferReportQueryForm extends PageForm {

    /**
     * 自有系统自定义的offerID
     */
    @ApiModelProperty("自有系统自定义的offerID")
    private String offerId;

    /**
     * 广告主提供的offerId
     */
    @ApiModelProperty("广告主提供的offerId")
    private String advOfferId;

    /**
     * offer名称
     */
    @ApiModelProperty("offer名称")
    private String offerName;

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
     * 客户ID
     */
    @ApiModelProperty("客户ID")
    private String custId;

    @ApiModelProperty(value = "日期")
    private List<String> date;

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

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
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

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }
}
