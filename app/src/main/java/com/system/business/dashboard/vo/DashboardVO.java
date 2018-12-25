package com.system.business.dashboard.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description = "首页面板数据对象")
public class DashboardVO {

    @ApiModelProperty("每日的INCOME/PV/UV数据")
    private List<Chart> completed;

    @ApiModelProperty("汇总数据")
    private List<TotalNumber> numbers;

    public List<Chart> getCompleted() {
        return completed;
    }

    public void setCompleted(List<Chart> completed) {
        this.completed = completed;
    }

    public List<TotalNumber> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<TotalNumber> numbers) {
        this.numbers = numbers;
    }
}
