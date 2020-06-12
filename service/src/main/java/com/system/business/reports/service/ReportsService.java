package com.system.business.reports.service;

import com.system.business.adv.offermanager.dto.OfferManagerQueryDto;
import com.system.business.operation.dto.OperationQueryDto;
import com.system.business.reports.dto.UploadExcelDto;
import com.system.business.reports.entity.EverydayIncomeEntity;
import com.system.business.reports.entity.OfferEntity;

import java.util.List;

public interface ReportsService {
    List<EverydayIncomeEntity> getEverydayIncome(OperationQueryDto queryDto);

    void uploadExcel(List<UploadExcelDto> list);

    List<OfferEntity> getOffers(OfferManagerQueryDto queryDto);
}
