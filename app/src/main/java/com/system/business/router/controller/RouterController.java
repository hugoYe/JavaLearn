package com.system.business.router.controller;

import com.system.common.constants.WebConstants;
import com.system.common.vo.ResponseVO;
import com.system.business.router.vo.RouteVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "Routes", description = "路由相关接口")
@RequestMapping(value = WebConstants.API_PREFIX + "/routes")
public class RouterController {

    @ApiOperation("获取路由列表")
    @GetMapping(value = "")
    public ResponseVO<List<RouteVO>> getRoutes() {
        List<RouteVO> list =  new ArrayList<>();
        RouteVO routeVO = new RouteVO();
        routeVO.setId("1");
        routeVO.setName("Dashboard");
        routeVO.setIcon("dashboard");
        routeVO.setZhName("仪表盘");
        routeVO.setRoute("/dashboard");
        list.add(routeVO);

        routeVO = new RouteVO();
        routeVO.setId("2");
        routeVO.setName("Income Per Day");
        routeVO.setIcon("bar-chart");
        routeVO.setZhName("每天收入");
        routeVO.setRoute("/income");
        routeVO.setBreadcrumbParentId("1");
        list.add(routeVO);

        routeVO = new RouteVO();
        routeVO.setId("3");
        routeVO.setName("Channels");
        routeVO.setIcon("appstore");
        routeVO.setZhName("渠道管理");
        routeVO.setRoute("/channels");
        routeVO.setBreadcrumbParentId("1");
        list.add(routeVO);

        routeVO = new RouteVO();
        routeVO.setId("4");
        routeVO.setName("Users");
        routeVO.setIcon("team");
        routeVO.setZhName("用户管理");
        routeVO.setRoute("/user");
        routeVO.setBreadcrumbParentId("1");
        list.add(routeVO);

        routeVO = new RouteVO();
        routeVO.setId("41");
        routeVO.setName("User Detail");
        routeVO.setZhName("用户详情");
        routeVO.setRoute("/user/:id");
        routeVO.setMenuParentId("-1");
        routeVO.setBreadcrumbParentId("4");
        list.add(routeVO);

        routeVO = new RouteVO();
        routeVO.setId("5");
        routeVO.setName("User Center");
        routeVO.setIcon("user");
        routeVO.setZhName("个人中心");
        routeVO.setRoute("/usercenter");
        routeVO.setBreadcrumbParentId("1");
        list.add(routeVO);

        return ResponseVO.successResponse(list);
    }
}
