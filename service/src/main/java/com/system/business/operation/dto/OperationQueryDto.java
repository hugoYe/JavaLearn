package com.system.business.operation.dto;

import com.system.common.dto.PageQueryDTO;

import java.util.List;

public class OperationQueryDto extends PageQueryDTO {

    /**
     * 用户id列表
     */
    private List<Integer> userIds;

    /**
     * 渠道id列表
     */
    private List<String> channelIds;

    /**
     * 日期
     */
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
