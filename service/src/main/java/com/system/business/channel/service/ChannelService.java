package com.system.business.channel.service;

import com.system.business.channel.dto.ChannelDto;

import java.util.List;

public interface ChannelService {

    /**
     * 添加渠道
     */
    Boolean addChannel(ChannelDto channelDto);

    /**
     * 删除渠道，只做逻辑删除，并非真的从数据库删除
     */
    Boolean deleteChannel(String channelId);

    /**
     * 更新渠道
     */
    Boolean updateChannel(ChannelDto channelDto);

    /**
     * 根据渠道id获取渠道
     */
    ChannelDto getChannel(String channelId);

    /**
     * 获取所有渠道
     */
    List<ChannelDto> getChannels();

}
