package com.system.business.adv.advertiser.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "广告主实例对象")
public class AdvertiserVO {

    /**
     * 广告主ID
     */
    @ApiModelProperty("广告主ID")
    private String advId;

    /**
     * 广告主名称
     */
    @ApiModelProperty("广告主名称")
    private String advName;

    /**
     * 广告主对接人-我司销售人员
     */
    @ApiModelProperty("广告主对接人-我司销售人员")
    private String advSales;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private String createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private String updateTime;

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

    public String getAdvSales() {
        return advSales;
    }

    public void setAdvSales(String advSales) {
        this.advSales = advSales;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
