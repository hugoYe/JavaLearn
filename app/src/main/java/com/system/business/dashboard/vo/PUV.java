package com.system.business.dashboard.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "PV/UV数据")
public class PUV {

    @ApiModelProperty("横轴")
    private Integer name;

    @ApiModelProperty("PV")
    private Integer PV;

    @ApiModelProperty("UV")
    private Integer UV;

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public Integer getPV() {
        return PV;
    }

    public void setPV(Integer PV) {
        this.PV = PV;
    }

    public Integer getUV() {
        return UV;
    }

    public void setUV(Integer UV) {
        this.UV = UV;
    }
}
