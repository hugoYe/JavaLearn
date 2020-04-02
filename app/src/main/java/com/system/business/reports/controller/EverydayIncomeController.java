package com.system.business.reports.controller;

import com.alibaba.excel.EasyExcel;
import com.system.business.operation.dto.OperationQueryDto;
import com.system.business.operation.form.OperationQueryForm;
import com.system.business.reports.dto.UploadExcelDto;
import com.system.business.reports.UploadExcelListener;
import com.system.business.reports.entity.EverydayIncomeEntity;
import com.system.business.reports.service.EverydayIncomeService;
import com.system.common.annotation.NoLogin;
import com.system.common.constants.WebConstants;
import com.system.common.support.XBeanUtil;
import com.system.common.utils.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

@RestController
@Api(tags = "reports", description = "报表相关接口")
@RequestMapping(value = WebConstants.API_PREFIX + "/reports")
public class EverydayIncomeController {

    @Autowired
    EverydayIncomeService everydayIncomeService;

    @NoLogin
    @ApiOperation("将每日收入导出生成excel报表")
    @GetMapping(value = "/exportEverydayIncome")
    public void exportEverydayIncome(OperationQueryForm form, HttpServletResponse response) throws IOException {

        OperationQueryDto queryDto = new OperationQueryDto();
        try {
            XBeanUtil.copyProperties(queryDto, form, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<EverydayIncomeEntity> list = everydayIncomeService.getEverydayIncome(queryDto);

        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = DateUtils.getFormatDate(new Date());
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        EasyExcel.write(response.getOutputStream(), EverydayIncomeEntity.class).sheet("收益").doWrite(list);

//        String fileName = "/Users/hugo/Documents/java/" + "simpleWrite" + ".xlsx";
//        EasyExcel.write(fileName, EverydayIncomeEntity.class).sheet("模板").doWrite(list);

    }

    @NoLogin
    @ApiOperation("导入excel报表数据到数据库")
    @PostMapping(value = "/upload")
    public void uploadExcel(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), UploadExcelDto.class, new UploadExcelListener(everydayIncomeService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
