package com.system.business.reports.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.system.business.reports.entity.EverydayIncomeEntity;
import com.system.business.reports.service.EverydayIncomeService;
import com.system.common.annotation.NoLogin;
import com.system.common.constants.WebConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
    public void exportEverydayIncome(HttpServletResponse response) throws IOException {

        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = "测试";
        try {
            fileName = URLEncoder.encode("测试", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        List<EverydayIncomeEntity> list = everydayIncomeService.getEverydayIncome();

//        String fileName = "/Users/hugo/Documents/java/" + "simpleWrite" + ".xlsx";

//        EasyExcel.write(fileName, EverydayIncomeEntity.class).sheet("模板").doWrite(list);
        EasyExcel.write(response.getOutputStream(), EverydayIncomeEntity.class).sheet("模板").doWrite(list);
    }
}
