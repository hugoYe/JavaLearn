package com.system.business.reports.service.impl;

import com.google.common.collect.Lists;
import com.system.business.reports.entity.EverydayIncomeEntity;
import com.system.business.reports.service.EverydayIncomeService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EverydayIncomeServiceImpl implements EverydayIncomeService {
    @Override
    public List<EverydayIncomeEntity> getEverydayIncome() {
        List<EverydayIncomeEntity> list = Lists.newArrayList();
        EverydayIncomeEntity e1 = new EverydayIncomeEntity();
        e1.setDate(new Date());
        e1.setIncome(111.11);
        e1.setPv(11111);
        e1.setUv(111111);
        e1.setRealIncome(119.11);
        e1.setUserId("wy001");
        e1.setChannleName("酷宇");
        list.add(e1);

        EverydayIncomeEntity e2 = new EverydayIncomeEntity();
        e2.setDate(new Date());
        e2.setIncome(222.22);
        e2.setPv(22222);
        e2.setUv(222222);
        e2.setRealIncome(229.22);
        e2.setUserId("wy002");
        e2.setChannleName("酷睿");
        list.add(e2);

        List<EverydayIncomeEntity> listAll = Lists.newArrayList();
        listAll.addAll(list);

        return listAll;
    }
}
