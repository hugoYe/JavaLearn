package com.system.income.controller;

import com.system.common.constants.WebConstants;
import com.system.common.utils.DateUtils;
import com.system.common.vo.ResponseVO;
import com.system.income.vo.IncomeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@Api(tags = "Income", description = "收入相关接口")
@RequestMapping(value = WebConstants.API_PREFIX + "/income")
public class IncomeController {

    @ApiOperation("获取收入")
    @GetMapping
    public ResponseVO<IncomeVO> getIncome(Integer page, Integer pageSize) {
        IncomeVO incomeVO = new IncomeVO();
        List<IncomeVO.Income> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 58; i++) {
            IncomeVO.Income income = new IncomeVO.Income();
            income.setDate(DateUtils.getFormatDate(DateUtils.randomDate("2017-1-1", "2018-12-30")));
            income.setChannelId("pad"+random.nextInt(500));
            income.setChannelName("phoenix");
            income.setPv(random.nextInt(10000));
            income.setUv(random.nextInt(10000));
            income.setIncome(random.nextInt(1000));
            list.add(income);
        }

        incomeVO.setTotal(list.size());
        incomeVO.setList(list);

        return ResponseVO.successResponse(incomeVO);
    }
}
