package com.system.business.operation.service;

import com.system.business.operation.dto.OperationDto;
import com.system.business.operation.dto.OperationQueryDto;
import com.system.common.dto.PageDTO;


public interface OperationService {

    Integer addIncome(OperationDto dto);

    PageDTO<OperationDto> getIncomeList(OperationQueryDto queryDto);
}
