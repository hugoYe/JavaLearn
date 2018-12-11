package com.system.business.router.controller;

import com.system.business.router.RouteConstant;
import com.system.business.router.vo.RouteVO;
import com.system.common.constants.WebConstants;
import com.system.common.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "Routes", description = "路由相关接口")
@RequestMapping(value = WebConstants.API_PREFIX + "/routes")
public class RouterController {

    @ApiOperation("获取路由列表")
    @GetMapping(value = "")
    public ResponseVO<List<RouteVO>> getRoutes() {

        return ResponseVO.successResponse(RouteConstant.manager_route_list);
    }
}
