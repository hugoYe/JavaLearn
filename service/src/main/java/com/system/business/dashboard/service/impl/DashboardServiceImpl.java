package com.system.business.dashboard.service.impl;

import com.system.business.dashboard.dto.DashboardDto;
import com.system.business.dashboard.service.DashboardService;
import com.system.business.operation.dao.OperationDao;
import com.system.business.operation.domain.OperationDomain;
import com.system.business.user.dao.UserDao;
import com.system.business.user.domain.UserDomain;
import com.system.common.constants.YesNoEnum;
import com.system.common.support.XBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private OperationDao operationDao;

    @Autowired
    private UserDao userDao;

    @Override
    public List<DashboardDto> queryDashboard(String userId) {

        UserDomain user = userDao.findByUserIdAndIsDeleted(userId, YesNoEnum.NO.getValue());

        List<OperationDomain> queryList = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        Date curDate = new Date();
        cal.setTime(curDate);
        cal.add(Calendar.MONTH, -1);
        Date preMonthDate = cal.getTime();
        if (user.getUserRole().equals("admin")) {
            queryList = operationDao.queryOneMonth(preMonthDate, curDate);
        } else {
            queryList = operationDao.queryOneMonthByUserId(user.getId(), preMonthDate, curDate);
        }

        List<DashboardDto> res = queryList.stream().map(operationDomain -> {
            DashboardDto dto = new DashboardDto();
            try {
                XBeanUtil.copyProperties(dto, operationDomain);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return dto;
        }).collect(Collectors.toList());

        return res;
    }
}
