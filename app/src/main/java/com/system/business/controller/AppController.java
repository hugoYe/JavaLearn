package com.system.business.controller;

import com.system.business.domain.AppInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "APP", description = "App相关接口")
@RequestMapping("/app")
public class AppController {

    @Autowired
    private AppInfo appInfo;

    @ApiOperation("获取app信息")
    @GetMapping("/getAppInfo")
    public AppInfo getAppInfo() {
        return appInfo;
    }

}
