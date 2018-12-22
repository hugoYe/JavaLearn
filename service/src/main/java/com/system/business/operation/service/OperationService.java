package com.system.business.operation.service;

import com.system.business.operation.dto.OperationDto;
import com.system.business.operation.dto.OperationQueryDto;
import com.system.business.userchannel.domain.UserAndChannelDomain;
import com.system.common.dto.PageDTO;

import java.util.List;


public interface OperationService {

    Integer addIncome(OperationDto dto);

    PageDTO<OperationDto> getIncomeList(OperationQueryDto queryDto);

    List<UserAndChannelDomain> getUserAndChannelDict();
}
