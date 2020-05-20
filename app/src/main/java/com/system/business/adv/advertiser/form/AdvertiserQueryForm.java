package com.system.business.adv.advertiser.form;

import com.system.common.form.PageForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel
public class AdvertiserQueryForm extends PageForm {

    @ApiModelProperty(value = "广告主ID")
    private String advId;

    @ApiModelProperty("广告主名称")
    private String advName;

    @ApiModelProperty("广告类型（cpa/cpi）")
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
