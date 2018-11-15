package com.system.common.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@ApiModel(description = "请求返回Vo")
public class ResponseVO<V> {

    @ApiModelProperty(value = "异常Code,可选", example = "null")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorCode = "0";

    @ApiModelProperty("业务是否成功")
    private boolean success;

    @ApiModelProperty("分字段出错信息，例如表单中某一个字段")
    private List<FieldExceptionVO> fieldErrors;

    @ApiModelProperty("全局出错信息")
    private String msg;

    @ApiModelProperty("返回内容")
    private V data;

    public ResponseVO() {
        super();
    }

    public ResponseVO(boolean success, List<FieldExceptionVO> fieldErrors, String msg) {
        super();
        this.success = success;
        this.fieldErrors = fieldErrors;
        this.msg = msg;
    }

    public ResponseVO(boolean success, List<FieldExceptionVO> fieldErrors, String msg, V data) {
        super();
        this.success = success;
        this.fieldErrors = fieldErrors;
        this.msg = msg;
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<FieldExceptionVO> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldExceptionVO> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public V getData() {
        return data;
    }

    public void setData(V data) {
        this.data = data;
    }

    public static <T> ResponseVO<T> successResponse() {
        return response(true, null, "请求成功！");
    }

    public static <T> ResponseVO<T> successResponse(T data) {
        return response(true, null, "请求成功！", data);
    }

    public static <T> ResponseVO<T> successResponse(T data, PropertyFilter... filters) {
        return response(true, null, "请求成功！", data, filters);
    }

    public static <T> ResponseVO<T> failResponse(String msg) {
        return new ResponseVO<>(false, null, msg);
    }

    private static <T> ResponseVO<T> response(boolean success, List<FieldExceptionVO> fieldErrors, String msg) {
        return new ResponseVO<>(success, fieldErrors, msg);
    }

    private static <T> ResponseVO<T> response(boolean success, List<FieldExceptionVO> fieldErrors, String msg, T data) {
        return new ResponseVO<>(success, fieldErrors, msg, data);
    }

    private static <T> ResponseVO<T> response(boolean success, List<FieldExceptionVO> fieldErrors, String msg,
                                              T data, PropertyFilter... filters) {
        return new ResponseVO<>(success, fieldErrors, msg, JSONObject.parseObject(JSON.toJSONString(data, filters,
                SerializerFeature.WriteMapNullValue),
                new TypeReference<T>() {
                }));
    }

    public void addFieldError(String fieldName, String errorMsg) {
        if (this.getFieldErrors() == null) {
            this.fieldErrors = new ArrayList<>();
        }
        this.fieldErrors.add(new FieldExceptionVO(fieldName, errorMsg));
    }
}
