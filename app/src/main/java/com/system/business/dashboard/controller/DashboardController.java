package com.system.business.dashboard.controller;

import com.system.common.constants.WebConstants;
import com.system.common.vo.ResponseVO;
import com.system.business.dashboard.vo.DashboardVO;
import com.system.business.dashboard.vo.PUV;
import com.system.business.dashboard.vo.TotalNumber;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@Api(tags = "Dashboard", description = "首页面板")
@RequestMapping(value = WebConstants.API_PREFIX + "/dashboard")
public class DashboardController {

    @ApiOperation("获取用户信息")
    @GetMapping(value = "")
    public ResponseVO<DashboardVO> queryDashboard() {
        DashboardVO dashboardVO = new DashboardVO();

        List<TotalNumber> numbers = new ArrayList<>();
        TotalNumber number = new TotalNumber();
        number.setIcon("dollar");
        number.setColor("#64ea91");
        number.setTitle("Yesterday Income");
        number.setNumber(2781);
        numbers.add(number);

        number = new TotalNumber();
        number.setIcon("area-chart");
        number.setColor("#d6fbb5");
        number.setTitle("Yesterday PV");
        number.setNumber(3241);
        numbers.add(number);

        number = new TotalNumber();
        number.setIcon("area-chart");
        number.setColor("#c1e0fc");
        number.setTitle("Yesterday UV");
        number.setNumber(2553);
        numbers.add(number);

        dashboardVO.setNumbers(numbers);

        List<PUV> puvs = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i < 31; i++) {
            PUV puv = new PUV();
            puv.setName(i);
            puv.setPV(random.nextInt(10000));
            puv.setUV(random.nextInt(10000));
            puvs.add(puv);
        }
        dashboardVO.setCompleted(puvs);

        return ResponseVO.successResponse(dashboardVO);
    }
}

