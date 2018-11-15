package com.system.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "字段异常信息")
public class FieldExceptionVO {

    @ApiModelProperty("字段名")
    private String fieldName;

    @ApiModelProperty("该字段出错信息")
    private String msg;

    public FieldExceptionVO(String fieldName, String msg) {
        this.fieldName = fieldName;
        this.msg = msg;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
