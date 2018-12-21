package com.system.business.operation.service.impl;

import com.system.business.operation.dao.OperationDao;
import com.system.business.operation.domain.OperationDomain;
import com.system.business.operation.dto.OperationDto;
import com.system.business.operation.dto.OperationQueryDto;
import com.system.business.operation.service.OperationService;
import com.system.common.constants.YesNoEnum;
import com.system.common.dto.PageDTO;
import com.system.common.support.XBeanUtil;
import com.system.common.utils.DateUtils;
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

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private OperationDao operationDao;

    @Override
    public PageDTO<OperationDto> getIncomeList(OperationQueryDto queryDto) {

        Specification<OperationDomain> spec = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            //默认查询未删除
            predicates.add(builder.equal(root.get("isDeleted"), YesNoEnum.NO.getValue()));

            List<Integer> userIds = queryDto.getUserIds();
            if (null != userIds && userIds.size() > 0) {
                predicates.add(builder.in(root.get("userId")).in(queryDto.getUserIds()));
            }

            List<String> channelIds = queryDto.getChannelIds();
            if (null != channelIds && channelIds.size() > 0) {
                predicates.add(builder.in(root.get("channelId")).in(channelIds));
            }

            List<String> dates = queryDto.getDate();
            if (null != dates && dates.size() > 0) {
                Date beginTime = DateUtils.parse(dates.get(0), DateUtils.DATETIME_FORMAT);
                Date endTime = DateUtils.parse(dates.get(1), DateUtils.DATETIME_FORMAT);
                predicates.add(builder.between(root.get("date"), beginTime, endTime));
            }

            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        };


        PageRequest request = new PageRequest(queryDto.getPage() - 1, queryDto.getPageSize(), Sort.Direction
                .fromString(queryDto.getOrderDesc()), "date");

        Page<OperationDomain> queryList = operationDao.findAll(spec, request);

        PageDTO<OperationDto> result = PageDTO.of(queryList, domain -> {
            OperationDto dto = new OperationDto();

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
}
