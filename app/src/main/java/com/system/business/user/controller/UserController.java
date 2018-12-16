package com.system.business.user.controller;

import com.system.business.permission.Permission;
import com.system.business.permission.Role;
import com.system.business.router.RouteConstant;
import com.system.business.user.dto.ModifyPasswordDTO;
import com.system.business.user.dto.UserDTO;
import com.system.business.user.form.LoginForm;
import com.system.business.user.form.ModifyPasswordForm;
import com.system.business.user.form.UserAddForm;
import com.system.business.user.form.UserEditForm;
import com.system.business.user.service.UserService;
import com.system.business.user.vo.LoginVO;
import com.system.business.user.vo.UserVO;
import com.system.business.user.vo.UsersVO;
import com.system.common.annotation.NoAuth;
import com.system.common.annotation.NoLogin;
import com.system.common.constants.WebConstants;
import com.system.common.constants.YesNoEnum;
import com.system.common.utils.DateUtils;
import com.system.common.utils.Jwtutils;
import com.system.common.vo.ResponseVO;
import com.system.exception.BizException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@Api(tags = "User", description = "用户相关接口")
@RequestMapping(value = WebConstants.API_PREFIX + "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @NoLogin
    @NoAuth
    @ApiOperation("用户登录")
    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseVO<LoginVO> login(@RequestBody LoginForm form, HttpServletRequest request) {
        UserDTO userDTO = userService.login(form.getName(), form.getPassword());
        LoginVO loginVO = new LoginVO();
        String token = Jwtutils.createJWT(Jwtutils.TOW_DAY, userDTO.getId());
        loginVO.setToken(token);
        return ResponseVO.successResponse(loginVO);
    }

    @ApiOperation("用户登出")
    @GetMapping(value = "/logout")
    @ResponseBody
    public ResponseVO<Integer> logout() {
        return ResponseVO.successResponse();
    }


    @ApiOperation("获取当前登入用户信息")
    @GetMapping(value = "/getUserInfo")
    @ResponseBody
    public ResponseVO<UserVO> getUserInfo(HttpServletRequest request, HttpServletResponse response) {

        Integer userId = Jwtutils.verifyToken(request, response);
        UserDTO user = userService.getUserById(userId);

        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setUserName(user.getName());
        userVO.setRealName(user.getRealName());
        Permission permission = new Permission();

        if (user.getIsRoot() == YesNoEnum.YES.getValue()) {
            permission.setRole(Role.ROLE_ADMIN);
            permission.setVisit(RouteConstant.MANAGER_ROUTE_IDS);
        } else {
            permission.setRole(Role.ROLE_VISTOR);
            permission.setVisit(RouteConstant.VISTOR_ROUTE_IDS);
        }
        userVO.setPermissions(permission);

        return ResponseVO.successResponse(userVO);
    }

    @ApiOperation("获取非管理员用户信息")
    @GetMapping(value = "/getUsers")
    @ResponseBody
    public ResponseVO<UsersVO> getUsers(Integer page, Integer pageSize) {
        UsersVO usersVO = new UsersVO();
        Random random = new Random();
        List<UserVO> list = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            UserVO user = new UserVO();
            user.setUserName("phoenix" + random.nextInt(100));
            user.setId(random.nextInt(100));
            user.setRealName("phoenix" + random.nextInt(100));
            user.setChannelId("pad" + random.nextInt(100));
            user.setChannelName("pad" + random.nextInt(100));
            user.setCreateTime(DateUtils.getFormatDate(DateUtils.randomDate("2017-1-1", "2018-12-30")));
            user.setCompany("上海" + random.nextInt(100));
            list.add(user);
        }

        usersVO.setTotal(list.size());
        usersVO.setList(list);

        return ResponseVO.successResponse(usersVO);

    }

    @ApiOperation("获取用户")
    @GetMapping(value = "getUser/{id}")
    @ResponseBody
    public ResponseVO<UserVO> getUserById(@PathVariable(name = "id") int id) {
        UserVO userVO = new UserVO();
        Random random = new Random();
        userVO.setUserName("phoenix" + random.nextInt(100));
        userVO.setId(random.nextInt(100));
        userVO.setRealName("phoenix" + random.nextInt(100));
        userVO.setChannelId("pad" + random.nextInt(100));
        userVO.setChannelName("pad" + random.nextInt(100));
        userVO.setCreateTime(DateUtils.getFormatDate(DateUtils.randomDate("2017-1-1", "2018-12-30")));
        userVO.setCompany("上海" + random.nextInt(100));

        return ResponseVO.successResponse(userVO);
    }

    @PostMapping
    @ApiOperation("添加用户")
    @ResponseBody
    public ResponseVO<Boolean> addUser(@RequestBody UserAddForm form) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(form, userDTO);
        userDTO.setName(form.getUserName());
        Boolean res = userService.addUser(userDTO);

        return ResponseVO.successResponse(res);
    }

    @PutMapping(value = "/edit/{id}")
    @ApiOperation("编辑用户")
    @ResponseBody
    public ResponseVO<Integer> editUser(@PathVariable(name = "id") int id, @RequestBody UserEditForm form) {
        if (StringUtils.isEmpty(form.getPassword()) || StringUtils.isEmpty(form.getConfirmPassword())) {
            throw new BizException("None password!");
        }
        if (!form.getPassword().equals(form.getConfirmPassword())) {
            throw new BizException("Entered passwords differ!");
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        BeanUtils.copyProperties(form, userDTO);
        Integer editId = userService.editUser(userDTO);

        return ResponseVO.successResponse(editId);
    }

    @PutMapping(value = "/modifyPassword")
    @ApiOperation("修改密码")
    @ResponseBody
    public ResponseVO<Integer> modifyPassword(@RequestBody ModifyPasswordForm form) {
        if (!form.getNewPassword().equals(form.getConfirmPassword())) {
            throw new BizException("Entered passwords differ!");
        }

        ModifyPasswordDTO dto = new ModifyPasswordDTO();
        BeanUtils.copyProperties(form, dto);
        Integer modifyId = userService.modifyPassword(dto);

        return ResponseVO.successResponse(modifyId);
    }

}
