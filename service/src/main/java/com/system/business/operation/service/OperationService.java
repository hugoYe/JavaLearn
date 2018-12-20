package com.system.business.operation.service;

import com.system.business.operation.dto.OperationDto;
import com.system.business.operation.dto.OperationQueryDto;
import com.system.common.dto.PageDTO;
import org.springframework.stereotype.Service;

@Service
public interface OperationService {

    PageDTO<OperationDto> getIncomeList(OperationQueryDto queryDto);
}
