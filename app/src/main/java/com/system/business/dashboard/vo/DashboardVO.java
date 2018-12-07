package com.system.business.dashboard.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description = "首页面板数据对象")
public class DashboardVO {

    @ApiModelProperty("每日的PV/UV数据")
    private List<PUV> completed;

    @ApiModelProperty("汇总数据")
    private List<TotalNumber> numbers;

    public List<PUV> getCompleted() {
        return completed;
    }

    public void setCompleted(List<PUV> completed) {
        this.completed = completed;
    }

    public List<TotalNumber> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<TotalNumber> numbers) {
        this.numbers = numbers;
    }
}
