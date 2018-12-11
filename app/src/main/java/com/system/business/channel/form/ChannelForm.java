package com.system.business.channel.form;

import io.swagger.annotations.ApiModelProperty;

public class ChannelForm {

    /**
     * 渠道id
     */
    @ApiModelProperty("渠道id")
    private String channelId;

    /**
     * 渠道名称
     */
    @ApiModelProperty("渠道名称")
    private String channelName;

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
}
