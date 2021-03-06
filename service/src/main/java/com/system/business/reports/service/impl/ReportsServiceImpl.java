package com.system.business.reports.service.impl;

import com.system.business.adv.offermanager.dao.OfferManagerDao;
import com.system.business.adv.offermanager.domain.OfferManagerDomain;
import com.system.business.adv.offermanager.dto.OfferManagerQueryDto;
import com.system.business.channel.dao.ChannelDao;
import com.system.business.channel.domain.ChannelDomain;
import com.system.business.operation.dao.OperationDao;
import com.system.business.operation.domain.OperationDomain;
import com.system.business.operation.dto.OperationDto;
import com.system.business.operation.dto.OperationQueryDto;
import com.system.business.reports.dto.UploadExcelDto;
import com.system.business.reports.entity.EverydayIncomeEntity;
import com.system.business.reports.entity.OfferEntity;
import com.system.business.reports.service.ReportsService;
import com.system.business.user.dao.UserDao;
import com.system.business.user.domain.UserDomain;
import com.system.business.userchannel.dao.UserAndChannelDao;
import com.system.business.userchannel.domain.UserAndChannelDomain;
import com.system.common.constants.YesNoEnum;
import com.system.common.support.XBeanUtil;
import com.system.common.utils.DateUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ReportsServiceImpl implements ReportsService {

    @Autowired
    private OperationDao operationDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ChannelDao channelDao;

    @Autowired
    private UserAndChannelDao userAndChannelDao;

    @Autowired
    private OfferManagerDao offerManagerDao;

    @Override
    public List<EverydayIncomeEntity> getEverydayIncome(OperationQueryDto queryDto) {
//        List<EverydayIncomeEntity> list = Lists.newArrayList();
//        EverydayIncomeEntity e1 = new EverydayIncomeEntity();
//        e1.setDate(new Date());
//        e1.setIncome(111.11);
//        e1.setPv(11111);
//        e1.setUv(111111);
//        e1.setRealIncome(119.11);
//        e1.setCustomerId("wy001");
//        e1.setChannelName("酷宇");
//        list.add(e1);
//
//        EverydayIncomeEntity e2 = new EverydayIncomeEntity();
//        e2.setDate(new Date());
//        e2.setIncome(222.22);
//        e2.setPv(22222);
//        e2.setUv(222222);
//        e2.setRealIncome(229.22);
//        e2.setCustomerId("wy002");
//        e2.setChannelName("酷睿");
//        list.add(e2);
//
//        List<EverydayIncomeEntity> listAll = Lists.newArrayList();
//        listAll.addAll(list);
//
//        return listAll;

        Specification<OperationDomain> spec = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            //默认查询未删除
            predicates.add(builder.equal(root.get("isDeleted"), YesNoEnum.NO.getValue()));

            List<Integer> userIds = queryDto.getUserIds();
            if (CollectionUtils.isNotEmpty(userIds)) {
                CriteriaBuilder.In<Integer> in = builder.in(root.get("userId"));
                for (Integer userId : userIds) {
                    in.value(userId);
                }
                predicates.add(in);
            }

            List<String> channelIds = queryDto.getChannelIds();
            if (CollectionUtils.isNotEmpty(channelIds)) {
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

        List<OperationDomain> l = operationDao.findAll(spec);
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

        List<OperationDto> listDto = l.stream().map(operationDomain -> {
            OperationDto dto = new OperationDto();

            try {
                XBeanUtil.copyProperties(dto, operationDomain, false);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (usersMap.containsKey(dto.getUserId())) {
                UserDomain user = usersMap.get(dto.getUserId());
                dto.setCustomerId(user.getUserId());
                dto.setIncomeRate(user.getIncomeRate());
                dto.setUserName(user.getName());
                dto.setRealName(user.getRealName());
            }

            if (channelsMap.containsKey(dto.getChannelId())) {
                dto.setChannelName(channelsMap.get(dto.getChannelId()).getChannelName());
            }

            return dto;
        }).collect(Collectors.toList());

        List<EverydayIncomeEntity> listAll = listDto.stream().map(dto -> {
            EverydayIncomeEntity entity = new EverydayIncomeEntity();

            try {
                XBeanUtil.copyProperties(entity, dto, false);
            } catch (Exception e) {
                e.printStackTrace();
            }

            entity.setDate(DateUtils.getFormatDate(dto.getDate()));

            return entity;

        }).collect(Collectors.toList());


        return listAll;
    }

    @Override
    public void uploadExcel(List<UploadExcelDto> list) {
        List<OperationDomain> saveList = list.stream().map(excelData -> {
            OperationDomain domain = new OperationDomain();

            domain.setDate(DateUtils.parse(excelData.getDate(), DateUtils.DATETIME_FORMAT));
            domain.setPv(excelData.getPv());
            domain.setUv(excelData.getUv());
            domain.setRealIncome(excelData.getRealIncome());

            UserDomain user = userDao.findByUserIdAndIsDeleted(excelData.getCustomerId(), YesNoEnum.NO.getValue());
            domain.setUserId(user.getId());

            domain.setIncome(domain.getRealIncome() * user.getIncomeRate() / 100);

            List<UserAndChannelDomain> userChannelList = userAndChannelDao.findByUserId(domain.getUserId());
            domain.setChannelId(userChannelList.get(0).getChannelId());

            domain.setIsDeleted(YesNoEnum.NO.getValue());

            return domain;
        }).collect(Collectors.toList());

        operationDao.save(saveList);
    }

    @Override
    public List<OfferEntity> getOffers(OfferManagerQueryDto queryDto) {

        Specification<OfferManagerDomain> spec = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            //默认查询未删除
            predicates.add(builder.equal(root.get("isDeleted"), YesNoEnum.NO.getValue()));
            if (queryDto.getOfferId() != null) {
                predicates.add(builder.equal(root.get("offerId"), queryDto.getOfferId()));
            }

            if (queryDto.getAdvOfferId() != null) {
                predicates.add(builder.equal(root.get("advOfferId"), queryDto.getAdvOfferId()));
            }

            if (queryDto.getOfferName() != null) {
                predicates.add(builder.equal(root.get("offerName"), queryDto.getOfferName()));
            }

            if (queryDto.getOfferType() != null) {
                predicates.add(builder.equal(root.get("offerType"), queryDto.getOfferType()));
            }

            if (queryDto.getCountry() != null) {
                predicates.add(builder.like(root.get("country"), "%" + queryDto.getCountry() + "%"));
            }

            if (queryDto.getCarrier() != null) {
                predicates.add(builder.like(root.get("carrier"), "%" + queryDto.getCarrier() + "%"));
            }

            if (queryDto.getAdvertiser() != null) {
                predicates.add(builder.equal(root.get("advertiser"), queryDto.getAdvertiser()));
            }

            if (queryDto.getStatus() != null) {
                predicates.add(builder.equal(root.get("status"), queryDto.getStatus()));
            }

            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        };

        List<OfferManagerDomain> all = offerManagerDao.findAll(spec);

        List<OfferEntity> result = all.stream().map(domain -> {

            OfferEntity entity = new OfferEntity();
            try {
                XBeanUtil.copyProperties(entity, domain, false);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return entity;
        }).collect(Collectors.toList());

        return result;
    }
}
