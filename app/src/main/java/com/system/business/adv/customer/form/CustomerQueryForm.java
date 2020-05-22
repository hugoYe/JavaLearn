package com.system.business.adv.customer.form;

import com.system.common.form.PageForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class CustomerQueryForm extends PageForm {

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
     * 客户对接广告类型（cpa/cpi）
     */
    @ApiModelProperty("客户对接广告类型（cpa/cpi）")
    private String custType;

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
}
