package com.system.business.adv.advertiser.form;

import io.swagger.annotations.ApiModelProperty;

public class AdvertiserForm {

    /**
     * 广告主ID
     */
    @ApiModelProperty("广告主ID")
    private String advId;

    /**
     * 广告主名称
     */
    @ApiModelProperty("广告主名称")
    private String advName;

    /**
     * 广告类型
     */
    @ApiModelProperty("广告类型")
    private String advType;

    /**
     * 广告主对接人-我司销售人员
     */
    @ApiModelProperty("广告主对接人-我司销售人员")
    private String advSales;

    /**
     * 广告主公司全称
     */
    @ApiModelProperty("广告主公司全称")
    private String advCompany;

    /**
     * 广告主联系人
     */
    @ApiModelProperty("广告主联系人")
    private String advContact;

    /**
     * 广告主联系方式
     */
    @ApiModelProperty("广告主联系方式")
    private String advPhone;

    /**
     * 广告主联系邮箱-支持多个填写邮箱，中间可以设定由;或者,隔开
     */
    @ApiModelProperty("广告主联系邮箱-支持多个填写邮箱，中间可以设定由;或者,隔开")
    private String advEmail;

    public String getAdvId() {
        return advId;
    }

    public void setAdvId(String advId) {
        this.advId = advId;
    }

    public String getAdvName() {
        return advName;
    }

    public void setAdvName(String advName) {
        this.advName = advName;
    }

    public String getAdvType() {
        return advType;
    }

    public void setAdvType(String advType) {
        this.advType = advType;
    }

    public String getAdvSales() {
        return advSales;
    }

    public void setAdvSales(String advSales) {
        this.advSales = advSales;
    }

    public String getAdvCompany() {
        return advCompany;
    }

    public void setAdvCompany(String advCompany) {
        this.advCompany = advCompany;
    }

    public String getAdvContact() {
        return advContact;
    }

    public void setAdvContact(String advContact) {
        this.advContact = advContact;
    }

    public String getAdvPhone() {
        return advPhone;
    }

    public void setAdvPhone(String advPhone) {
        this.advPhone = advPhone;
    }

    public String getAdvEmail() {
        return advEmail;
    }

    public void setAdvEmail(String advEmail) {
        this.advEmail = advEmail;
    }
}
