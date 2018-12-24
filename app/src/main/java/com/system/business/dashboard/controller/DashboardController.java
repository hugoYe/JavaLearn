package com.system.business.dashboard.controller;

import com.system.business.dashboard.dto.DashboardDto;
import com.system.business.dashboard.service.DashboardService;
import com.system.business.dashboard.vo.DashboardVO;
import com.system.business.dashboard.vo.PUV;
import com.system.business.dashboard.vo.TotalNumber;
import com.system.common.constants.WebConstants;
import com.system.common.utils.DateUtils;
import com.system.common.utils.Jwtutils;
import com.system.common.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@Api(tags = "Dashboard", description = "首页面板")
@RequestMapping(value = WebConstants.API_PREFIX + "/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @ApiOperation("获取面板信息")
    @GetMapping
    public ResponseVO<DashboardVO> queryDashboard(HttpServletRequest request, HttpServletResponse response) {

        Integer userId = Jwtutils.verifyToken(request, response);
        List<DashboardDto> queryList = dashboardService.queryDashboard(userId);
        Map<Date, List<DashboardDto>> queryMap = new HashMap<>();
        for (DashboardDto dto : queryList) {
            Date key = dto.getDate();
            if (queryMap.containsKey(key)) {
                List<DashboardDto> l = queryMap.get(key);
                l.add(dto);
            } else {
                List<DashboardDto> l = new ArrayList<>();
                l.add(dto);
                queryMap.put(key, l);
            }
        }

        DashboardVO dashboardVO = new DashboardVO();

        // 获取昨天数据
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date yesterday = DateUtils.parseDate(cal.getTime());
        Double totalIncome = 0d;
        Double totalPv = 0d;
        Double totalUv = 0d;
        if (queryMap.containsKey(yesterday)) {
            List<DashboardDto> l = queryMap.get(yesterday);
            for (DashboardDto dto : l) {
                totalIncome = totalIncome + dto.getIncome();
                totalPv = totalPv + dto.getPv();
                totalUv = totalUv + dto.getUv();
            }
        }

        List<TotalNumber> numbers = new ArrayList<>();
        TotalNumber number = new TotalNumber();
        number.setIcon("dollar");
        number.setColor("#64ea91");
        number.setTitle("Yesterday Income");
        number.setNumber(totalIncome);
        numbers.add(number);

        number = new TotalNumber();
        number.setIcon("area-chart");
        number.setColor("#d6fbb5");
        number.setTitle("Yesterday PV");
        number.setNumber(totalPv);
        numbers.add(number);

        number = new TotalNumber();
        number.setIcon("area-chart");
        number.setColor("#c1e0fc");
        number.setTitle("Yesterday UV");
        number.setNumber(totalUv);
        numbers.add(number);

        dashboardVO.setNumbers(numbers);

        // 获取当天前30天日期信息
        cal.clear();
        cal.setTime(new Date());
        List<Date> listDate = new ArrayList<>();
        List<Integer> listDay = new ArrayList<>();
        for (int i = 1; i < 31; i++) {
            cal.add(Calendar.DATE, -1);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            listDay.add(day);
            Date date = cal.getTime();
            listDate.add(DateUtils.parseDate(date));
        }


        List<PUV> puvs = new ArrayList<>();
        for (int i = listDate.size() - 1; i >= 0; i--) {
            PUV puv = new PUV();
            puv.setName(listDay.get(i));
            puv.setPV(0);
            puv.setUV(0);
            Date date = listDate.get(i);
            if (queryMap.containsKey(date)) {
                List<DashboardDto> l = queryMap.get(date);
                for (DashboardDto dto : l) {
                    int pv = dto.getPv() + puv.getPV();
                    int uv = dto.getUv() + puv.getUV();
                    puv.setPV(pv);
                    puv.setUV(uv);
                }
            }

            puvs.add(puv);
        }

        dashboardVO.setCompleted(puvs);

        return ResponseVO.successResponse(dashboardVO);
    }
}

