package com.system.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;


@ApiModel(description = "列表数据")
public class ListVO<V> {
    @ApiModelProperty("总记录数")
    private int total;


    @ApiModelProperty("数据集")
    private  List<V> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<V> getList() {
        return list;
    }

    public void setList(List<V> list) {
        this.list = list;
    }
}
