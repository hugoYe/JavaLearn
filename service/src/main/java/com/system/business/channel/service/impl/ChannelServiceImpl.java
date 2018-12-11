package com.system.business.channel.service.impl;

import com.system.business.channel.dao.ChannelDao;
import com.system.business.channel.domain.ChannelDomain;
import com.system.business.channel.dto.ChannelDto;
import com.system.business.channel.service.ChannelService;
import com.system.common.constants.YesNoEnum;
import com.system.common.support.XBeanUtil;
import com.system.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    ChannelDao channelDao;

    @Override
    public Boolean addChannel(ChannelDto channelDto) {
        ChannelDomain exist = channelDao.findByChannelId(channelDto.getChannelId());
        if (null != exist) {
            throw new BizException("channel.channelId.exist");
        }

        ChannelDomain domain = new ChannelDomain();
        try {
            XBeanUtil.copyProperties(domain, channelDto, false);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException();
        }

        channelDao.save(domain);

        return true;
    }

    @Override
    public Boolean deleteChannel(String channelId) {
        ChannelDomain exist = channelDao.findByChannelIdAndIsDeleted(channelId, YesNoEnum.NO.getValue());
        if (null == exist) {
            throw new BizException("channel.not.exist");
        }

        exist.setIsDeleted(YesNoEnum.YES.getValue());
        channelDao.save(exist);

        return true;
    }

    @Override
    public Boolean updateChannel(ChannelDto channelDto) {
        ChannelDomain exist = channelDao.findByChannelIdAndIsDeleted(channelDto.getChannelId(), YesNoEnum.NO.getValue());
        if (null == exist) {
            throw new BizException("channel.not.exist");
        }

        try {
            XBeanUtil.copyProperties(exist, channelDto, false);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException();
        }

        channelDao.save(exist);

        return true;
    }

    @Override
    public ChannelDto getChannel(String channelId) {
        ChannelDomain exist = channelDao.findByChannelIdAndIsDeleted(channelId, YesNoEnum.NO.getValue());
        if (null == exist) {
            throw new BizException("channel.not.exist");
        }

        ChannelDto channel = new ChannelDto();
        try {
            XBeanUtil.copyProperties(channel, exist, false);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException();
        }

        return channel;
    }

    @Override
    public List<ChannelDto> getChannels() {
        List<ChannelDomain> list = channelDao.findAllChannels();
        List<ChannelDto> list1 = new ArrayList<>();
        for (ChannelDomain domain : list) {
            ChannelDto dto = new ChannelDto();
            try {
                XBeanUtil.copyProperties(dto, domain, false);
            } catch (Exception e) {
                e.printStackTrace();
                throw new BizException();
            }
            list1.add(dto);
        }

        return list1;
    }

}
