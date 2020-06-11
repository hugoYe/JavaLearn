package com.system.business.adv.offermanager.service;

import com.system.business.adv.customer.dto.CustomerDto;
import com.system.business.adv.customer.dto.CustomerQueryDto;
import com.system.business.adv.offermanager.dto.OfferManagerDto;
import com.system.business.adv.offermanager.dto.OfferManagerQueryDto;
import com.system.business.user.dto.UserEditDTO;
import com.system.common.dto.PageDTO;

import java.util.List;


public interface OfferManagerService {

    /**
     * 添加offer
     */
    Boolean addOffer(OfferManagerDto dto);

    /**
     * 删除offer，只做逻辑删除，并非真的从数据库删除
     */
    Boolean deleteOffer(String offerId);

    /**
     * 更新offer
     */
    Boolean updateOffer(OfferManagerDto dto);


    /**
     * 获取offer列表
     */
    PageDTO<OfferManagerDto> getOfferList(OfferManagerQueryDto pageQueryDTO);


    List<OfferManagerDto> getOfferDict();

}
