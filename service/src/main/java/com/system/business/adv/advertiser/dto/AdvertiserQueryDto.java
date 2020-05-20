package com.system.business.adv.advertiser.dto;

import com.system.common.dto.PageQueryDTO;

public class AdvertiserQueryDto extends PageQueryDTO {

    /**
     * 广告主ID
     */
    private String advId;

    /**
     * 广告主名称
     */
    private String advName;

    /**
     * "广告类型（cpa/cpi
     */
    private String advType;

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
}
