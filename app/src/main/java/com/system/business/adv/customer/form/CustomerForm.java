package com.system.business.adv.customer.form;

import io.swagger.annotations.ApiModelProperty;

public class CustomerForm {

    /**
     * 客户ID
     */
    @ApiModelProperty("客户ID")
    private String custId;

    /**
     * 客户名称
     */
    @ApiModelProperty("客户名称")
    private String custName;

    /**
     * 客户类型
     */
    @ApiModelProperty("客户类型")
    private String custType;

    /**
     * 客户对接人-我司销售人员
     */
    @ApiModelProperty("客户对接人-我司销售人员")
    private String custSales;

    /**
     * 客户公司全称
     */
    @ApiModelProperty("客户公司全称")
    private String custCompany;

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
     * 客户联系邮箱-支持多个填写邮箱，中间可以设定由;或者,隔开
     */
    @ApiModelProperty("客户联系邮箱-支持多个填写邮箱，中间可以设定由;或者,隔开")
    private String custEmail;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

    public String getCustSales() {
        return custSales;
    }

    public void setCustSales(String custSales) {
        this.custSales = custSales;
    }

    public String getCustCompany() {
        return custCompany;
    }

    public void setCustCompany(String custCompany) {
        this.custCompany = custCompany;
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

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }
}
