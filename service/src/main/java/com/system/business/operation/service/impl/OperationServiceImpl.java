package com.system.business.operation.service.impl;

import com.system.business.channel.dao.ChannelDao;
import com.system.business.channel.domain.ChannelDomain;
import com.system.business.operation.dao.OperationDao;
import com.system.business.operation.domain.OperationDomain;
import com.system.business.operation.dto.OperationDto;
import com.system.business.operation.dto.OperationQueryDto;
import com.system.business.operation.service.OperationService;
import com.system.business.user.dao.UserDao;
import com.system.business.user.domain.UserDomain;
import com.system.business.userchannel.dao.UserAndChannelDao;
import com.system.business.userchannel.domain.UserAndChannelDomain;
import com.system.common.constants.YesNoEnum;
import com.system.common.dto.PageDTO;
import com.system.common.support.XBeanUtil;
import com.system.common.utils.DateUtils;
import com.system.exception.BizException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private OperationDao operationDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ChannelDao channelDao;

    @Autowired
    private UserAndChannelDao userAndChannelDao;

    @Override
    public Integer addIncome(OperationDto dto) {

        OperationDomain exist = operationDao.findByUserIdAndChannelIdAndDate(dto.getUserId(), dto.getChannelId(), dto.getDate());
        if (null != exist) {
            throw new BizException("operation.record.exist");
        }

        OperationDomain save = new OperationDomain();
        try {
            XBeanUtil.copyProperties(save, dto, false);
        } catch (Exception e) {
            throw new BizException();
        }
        save.setIsDeleted(YesNoEnum.NO.getValue());

        operationDao.save(save);

        return null;
    }

    @Override
    public PageDTO<OperationDto> getIncomeList(OperationQueryDto queryDto) {

        Specification<OperationDomain> spec = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            //默认查询未删除
            predicates.add(builder.equal(root.get("isDeleted"), YesNoEnum.NO.getValue()));

            List<Integer> userIds = queryDto.getUserIds();
            if (CollectionUtils.isNotEmpty(userIds)) {
//                predicates.add(builder.in(root.get("userId")).in(queryDto.getUserIds()));
                CriteriaBuilder.In<Integer> in = builder.in(root.get("userId"));
                for (Integer userId : userIds) {
                    in.value(userId);
                }
                predicates.add(in);
            }

            List<String> channelIds = queryDto.getChannelIds();
            if (CollectionUtils.isNotEmpty(channelIds)) {
//                predicates.add(builder.in(root.get("channelId")).in(channelIds));
                CriteriaBuilder.In<String> in = builder.in(root.get("channelId"));
                for (String channelId : channelIds) {
                    in.value(channelId);
                }
                predicates.add(in);
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
        List<OperationDomain> l = queryList.getContent();
        List<Integer> userIds = l.stream().map(OperationDomain::getUserId).collect(Collectors.toList());
        List<String> channelIds = l.stream().map(OperationDomain::getChannelId).collect(Collectors.toList());
        List<UserDomain> users = new ArrayList<>();
        try {
            users = userDao.findByIds(userIds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<ChannelDomain> channels = new ArrayList<>();
        try {
            channels = channelDao.queryChannelByIds(channelIds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<Integer, UserDomain> usersMap = users.stream().collect(Collectors.toMap(UserDomain::getId, Function.identity()));
        Map<String, ChannelDomain> channelsMap = channels.stream().collect(Collectors.toMap(ChannelDomain::getChannelId, Function.identity()));

        PageDTO<OperationDto> result = PageDTO.of(queryList, domain -> {
            OperationDto dto = new OperationDto();

            try {
                XBeanUtil.copyProperties(dto, domain, false);
            } catch (Exception e) {
                e.printStackTrace();
                throw new BizException();
            }

            if (usersMap.containsKey(dto.getUserId())) {
                UserDomain user = usersMap.get(dto.getUserId());
                dto.setUserName(user.getName());
                dto.setRealName(user.getRealName());
            }

            if (channelsMap.containsKey(dto.getChannelId())) {
                dto.setChannelName(channelsMap.get(dto.getChannelId()).getChannelName());
            }

            return dto;
        });

        return result;
    }

    @Override
    public List<UserAndChannelDomain> getUserAndChannelDict() {
        List<UserAndChannelDomain> list = userAndChannelDao.findAllByIsDeleted(YesNoEnum.NO.getValue());
        return list;
    }
}
