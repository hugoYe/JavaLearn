package com.system.business.reports;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.system.business.reports.dto.UploadExcelDto;
import com.system.business.reports.service.EverydayIncomeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UploadExcelListener extends AnalysisEventListener<UploadExcelDto> {

    private static final int BATCH_COUNT = 100;
    EverydayIncomeService everydayIncomeService;
    List<UploadExcelDto> list = new ArrayList<>();

    public UploadExcelListener(EverydayIncomeService everydayIncomeService) {
        this.everydayIncomeService = everydayIncomeService;
    }


    /**
     * 这个每一条数据解析都会来调用
     */
    @Override
    public void invoke(UploadExcelDto uploadExcelData, AnalysisContext analysisContext) {
        list.add(uploadExcelData);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
    }

    private void saveData() {
        everydayIncomeService.uploadExcel(list);
    }
}
