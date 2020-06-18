package com.system.business.adv.apioffer.form;

import com.system.common.form.PageForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ApiOfferQueryForm extends PageForm {

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
     * offer对应国家
     */
    @ApiModelProperty("offer对应国家")
    private String country;

    /**
     * offer对应运营商
     */
    @ApiModelProperty("offer对应运营商")
    private String carrier;


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

}
