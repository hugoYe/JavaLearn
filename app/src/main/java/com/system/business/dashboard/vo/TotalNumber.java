package com.system.business.dashboard.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "首页展示数据")
public class TotalNumber {

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("图标颜色")
    private String color;

    @ApiModelProperty("数据标题")
    private String title;

    @ApiModelProperty("具体数据")
    private Integer number;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
