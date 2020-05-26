package com.system.business.adv.advertiser.service.impl;

import com.system.business.adv.advertiser.dao.AdvertiserDao;
import com.system.business.adv.advertiser.domain.AdvertiserDomain;
import com.system.business.adv.advertiser.dto.AdvertiserDto;
import com.system.business.adv.advertiser.dto.AdvertiserQueryDto;
import com.system.business.adv.advertiser.service.AdvertiserService;
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
public class AdvertiserServiceImpl implements AdvertiserService {

    @Autowired
    AdvertiserDao advertiserDao;

    @Override
    public Boolean addAdvertiser(AdvertiserDto advertiserDto) {
        AdvertiserDomain domain = new AdvertiserDomain();
        try {
            XBeanUtil.copyProperties(domain, advertiserDto, false);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException();
        }

        long count = advertiserDao.count();
        long newId = count + 1;
        String advId;
        if (newId < 100) {
            advId = autoGenericCode(String.valueOf(newId));
        } else {
            advId = String.valueOf(newId);
        }

        domain.setAdvId("A" + advId);

        advertiserDao.save(domain);

        return true;
    }

    private String autoGenericCode(String code) {
        String result = "";
        // 保留code的位数
        result = String.format("%0" + 3 + "d", Integer.parseInt(code));

        return result;
    }

    @Override
    public Boolean deleteAdvertiser(String advId) {
        AdvertiserDomain exist = advertiserDao.findByAdvIdAndIsDeleted(advId, YesNoEnum.NO.getValue());
        if (null == exist) {
            throw new BizException("advertiser.not.exist");
        }

        exist.setIsDeleted(YesNoEnum.YES.getValue());
        exist.setUpdateTime(new Date());

        advertiserDao.save(exist);

        return true;
    }

    @Override
    public Boolean updateAdvertiser(AdvertiserDto advertiserDto) {
        String advId = advertiserDto.getAdvId();
        AdvertiserDomain exist = advertiserDao.findByAdvIdAndIsDeleted(advId, YesNoEnum.NO.getValue());
        if (null == exist) {
            throw new BizException("advertiser.not.exist");
        }

        try {
            XBeanUtil.copyProperties(exist, advertiserDto, false);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException();
        }

        exist.setUpdateTime(new Date());

        advertiserDao.save(exist);

        return true;
    }

    @Override
    public AdvertiserDto getAdvertiser(String advId) {
        return null;
    }

    @Override
    public PageDTO<AdvertiserDto> getAdvertiserList(AdvertiserQueryDto pageQueryDTO) {

        Specification<AdvertiserDomain> spec = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            //默认查询未删除
            predicates.add(builder.equal(root.get("isDeleted"), YesNoEnum.NO.getValue()));
            if (pageQueryDTO.getAdvId() != null) {
                predicates.add(builder.equal(root.get("advId"), pageQueryDTO.getAdvId()));
            }

            if (pageQueryDTO.getAdvName() != null) {
                predicates.add(builder.equal(root.get("advName"), pageQueryDTO.getAdvName()));
            }

            if (pageQueryDTO.getAdvType() != null) {
                predicates.add(builder.equal(root.get("advType"), pageQueryDTO.getAdvType()));
            }

            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        };

        // 指定查询页面以及排序
        PageRequest request = new PageRequest(pageQueryDTO.getPage() - 1, pageQueryDTO.getPageSize(), Sort.Direction
                .fromString(pageQueryDTO.getOrderDesc()), pageQueryDTO.getOrder());

        Page<AdvertiserDomain> findList = advertiserDao.findAll(spec, request);

        PageDTO<AdvertiserDto> result = PageDTO.of(findList, domain -> {
            AdvertiserDto dto = new AdvertiserDto();

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
    public List<AdvertiserDto> getAdvertiserDict() {
        List<AdvertiserDomain> find = advertiserDao.findAllAdvertiser();
        List<AdvertiserDto> res = find.stream().map(domain -> {
            AdvertiserDto dto = new AdvertiserDto();
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
