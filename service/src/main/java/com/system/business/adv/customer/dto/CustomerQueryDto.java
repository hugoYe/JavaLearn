package com.system.business.adv.customer.dto;

import com.system.common.dto.PageQueryDTO;

public class CustomerQueryDto extends PageQueryDTO {

    /**
     * 客户ID
     */
    private String custId;

    /**
     * 客户名称
     */
    private String custName;

    /**
     * 客户对接广告类型（cpa/cpi）
     */
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
