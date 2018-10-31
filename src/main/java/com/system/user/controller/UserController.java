package com.system.user.controller;

import com.system.user.entity.User;
import com.system.user.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/user")
@Api(tags = "user", description = "用户相关接口")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "user/index";
    }

    @ApiOperation("根据ID获取用户信息")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", name = "id",
            dataType = "int", required = true, value = "用户的id", defaultValue = "1")})
    @ApiResponses({@ApiResponse(code = 400, message = "请求参数没填好"), @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")})
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    @ResponseBody
    public User getUserInfo(@RequestParam("id") int id) {
        if (id == 1) {
            return userService.findUserByName("yezhennan");
        }

        return new User();
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    @ResponseBody
    public String show(@RequestParam(value = "name") String name) {
        User user = userService.findUserByName(name);
        if (null != user)
            return user.getId() + "/" + user.getName() + "/" + user.getPassword();
        else return "null";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody User u) {
        return "success";
    }
}
