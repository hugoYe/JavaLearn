package com.system.business.operation.form;

import com.system.common.form.PageForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel
public class OperationQueryForm extends PageForm {

    @ApiModelProperty(value = "用户id列表")
    private List<Integer> userIds;

    @ApiModelProperty(value = "渠道id列表")
    private List<String> channelIds;

    @ApiModelProperty(value = "日期")
    private List<String> date;

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }

    public List<String> getChannelIds() {
        return channelIds;
    }

    public void setChannelIds(List<String> channelIds) {
        this.channelIds = channelIds;
    }

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }
}
