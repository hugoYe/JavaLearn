package com.system.business.dashboard.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "PV/UV数据")
public class Chart {

    @ApiModelProperty("横轴")
    private Integer xAxis;

    @ApiModelProperty("收入")
    private Double income;

    @ApiModelProperty("PV")
    private Integer PV;

    @ApiModelProperty("UV")
    private Integer UV;

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Integer getxAxis() {
        return xAxis;
    }

    public void setxAxis(Integer xAxis) {
        this.xAxis = xAxis;
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
