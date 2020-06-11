package com.system.business.adv.offermanager.service.impl;

import com.system.business.adv.offermanager.dao.OfferManagerDao;
import com.system.business.adv.offermanager.domain.OfferManagerDomain;
import com.system.business.adv.offermanager.dto.OfferManagerDto;
import com.system.business.adv.offermanager.dto.OfferManagerQueryDto;
import com.system.business.adv.offermanager.service.OfferManagerService;

import com.system.common.constants.YesNoEnum;
import com.system.common.dto.PageDTO;
import com.system.common.support.XBeanUtil;
import com.system.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OfferManagerServiceImpl implements OfferManagerService {

    @Autowired
    OfferManagerDao offerManagerDao;

    @Override
    public Boolean addOffer(OfferManagerDto dto) {
        OfferManagerDomain domain = new OfferManagerDomain();
        try {
            XBeanUtil.copyProperties(domain, dto, false);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException();
        }
        long count = offerManagerDao.count();
        long newId = count + 1 + 100000;
        domain.setOfferId(newId + "");

        offerManagerDao.save(domain);

        return true;
    }

    @Override
    public Boolean deleteOffer(String offerId) {
        OfferManagerDomain exist = offerManagerDao.findByOfferIdAndIsDeleted(offerId, YesNoEnum.NO.getValue());
        if (null == exist) {
            throw new BizException("offer.not.exist");
        }
        exist.setIsDeleted(YesNoEnum.YES.getValue());
        exist.setUpdateTime(new Date());

        offerManagerDao.save(exist);

        return true;
    }

    @Override
    public Boolean updateOffer(OfferManagerDto dto) {
        OfferManagerDomain exist = offerManagerDao.findByOfferIdAndIsDeleted(dto.getOfferId(), YesNoEnum.NO.getValue());
        if (null == exist) {
            throw new BizException("offer.not.exist");
        }
        try {
            XBeanUtil.copyProperties(exist, dto, false);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException();
        }
        exist.setUpdateTime(new Date());

        offerManagerDao.save(exist);

        return true;
    }


    @Override
    public PageDTO<OfferManagerDto> getOfferList(OfferManagerQueryDto pageQueryDTO) {

        Specification<OfferManagerDomain> spec = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            //默认查询未删除
            predicates.add(builder.equal(root.get("isDeleted"), YesNoEnum.NO.getValue()));
            if (pageQueryDTO.getOfferId() != null) {
                predicates.add(builder.equal(root.get("offerId"), pageQueryDTO.getOfferId()));
            }

            if (pageQueryDTO.getAdvOfferId() != null) {
                predicates.add(builder.equal(root.get("advOfferId"), pageQueryDTO.getAdvOfferId()));
            }

            if (pageQueryDTO.getOfferName() != null) {
                predicates.add(builder.equal(root.get("offerName"), pageQueryDTO.getOfferName()));
            }

            if (pageQueryDTO.getOfferType() != null) {
                predicates.add(builder.equal(root.get("offerType"), pageQueryDTO.getOfferType()));
            }

            if (pageQueryDTO.getCountry() != null) {
                predicates.add(builder.equal(root.get("country"), pageQueryDTO.getCountry()));
            }

            if (pageQueryDTO.getCarrier() != null) {
                predicates.add(builder.equal(root.get("carrier"), pageQueryDTO.getCarrier()));
            }

            if (pageQueryDTO.getAdvertiser() != null) {
                predicates.add(builder.equal(root.get("advertiser"), pageQueryDTO.getAdvertiser()));
            }

            if (pageQueryDTO.getStatus() != null) {
                predicates.add(builder.equal(root.get("status"), pageQueryDTO.getStatus()));
            }

            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        };

        // 指定查询页面以及排序
        PageRequest request = new PageRequest(pageQueryDTO.getPage() - 1, pageQueryDTO.getPageSize(), Sort.Direction
                .fromString(pageQueryDTO.getOrderDesc()), pageQueryDTO.getOrder());

        Page<OfferManagerDomain> findList = offerManagerDao.findAll(spec, request);

        PageDTO<OfferManagerDto> result = PageDTO.of(findList, domain -> {
            OfferManagerDto dto = new OfferManagerDto();
            try {
                XBeanUtil.copyProperties(dto, domain, false);
            } catch (Exception e) {
                e.printStackTrace();
                throw new BizException();
            }

            return dto;
        });

        return result;
    }


    @Override
    public List<OfferManagerDto> getOfferDict() {
        List<OfferManagerDomain> find = offerManagerDao.findAllOffer();
        List<OfferManagerDto> res = find.stream().map(domain -> {
            OfferManagerDto dto = new OfferManagerDto();
            try {
                XBeanUtil.copyProperties(dto, domain, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return dto;
        }).collect(Collectors.toList());

        return res;
    }

}
