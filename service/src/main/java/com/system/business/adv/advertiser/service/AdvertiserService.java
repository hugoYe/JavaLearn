package com.system.business.adv.advertiser.service;

import com.system.business.adv.advertiser.dto.AdvertiserDto;
import com.system.business.adv.advertiser.dto.AdvertiserQueryDto;
import com.system.common.dto.PageDTO;


public interface AdvertiserService {

    /**
     * 添加广告主
     */
    Boolean addAdvertiser(AdvertiserDto advertiserDto);

    /**
     * 删除广告主，只做逻辑删除，并非真的从数据库删除
     */
    Boolean deleteAdvertiser(String advId);

    /**
     * 更新广告主
     */
    Boolean updateAdvertiser(AdvertiserDto advertiserDto);

    /**
     * 根据广告主id获取广告主
     */
    AdvertiserDto getAdvertiser(String advId);

    /**
     * 获取广告主列表
     */
    PageDTO<AdvertiserDto> getAdvertiserList(AdvertiserQueryDto pageQueryDTO);

}
