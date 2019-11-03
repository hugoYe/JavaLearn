package com.system.business.reports.service;

import com.system.business.operation.dto.OperationQueryDto;
import com.system.business.reports.entity.EverydayIncomeEntity;

import java.util.List;

public interface EverydayIncomeService {
    List<EverydayIncomeEntity> getEverydayIncome(OperationQueryDto queryDto);
}
