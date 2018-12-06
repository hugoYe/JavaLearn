package com.system.income.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description = "收入实体对象")
public class IncomeVO {

    @ApiModelProperty("收入总记录数")
    private Integer total;

    @ApiModelProperty("收入详情")
    private List<Income> list;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Income> getList() {
        return list;
    }

    public void setList(List<Income> list) {
        this.list = list;
    }

    public static class Income {

        @ApiModelProperty("日期")
        private String date;

        @ApiModelProperty("渠道id")
        private String channelId;

        @ApiModelProperty("渠道名称")
        private String channelName;

        @ApiModelProperty("pv")
        private Integer pv;

        @ApiModelProperty("uv")
        private Integer uv;

        @ApiModelProperty("income")
        private Integer income;


        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getChannelId() {
            return channelId;
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }

        public String getChannelName() {
            return channelName;
        }

        public void setChannelName(String channelName) {
            this.channelName = channelName;
        }

        public Integer getPv() {
            return pv;
        }

        public void setPv(Integer pv) {
            this.pv = pv;
        }

        public Integer getUv() {
            return uv;
        }

        public void setUv(Integer uv) {
            this.uv = uv;
        }

        public Integer getIncome() {
            return income;
        }

        public void setIncome(Integer income) {
            this.income = income;
        }
    }
}
