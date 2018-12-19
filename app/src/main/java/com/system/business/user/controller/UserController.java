package com.system.business.user.controller;

import com.system.business.permission.Permission;
import com.system.business.permission.Role;
import com.system.business.router.RouteConstant;
import com.system.business.user.dto.UserDTO;
import com.system.business.user.dto.UserEditDTO;
import com.system.business.user.dto.UserQueryDto;
import com.system.business.user.form.LoginForm;
import com.system.business.user.form.UserEditForm;
import com.system.business.user.form.UserForm;
import com.system.business.user.form.UserQueryForm;
import com.system.business.user.service.UserService;
import com.system.business.user.vo.LoginVO;
import com.system.business.user.vo.UserVO;
import com.system.common.annotation.NoAuth;
import com.system.common.annotation.NoLogin;
import com.system.common.constants.WebConstants;
import com.system.common.constants.YesNoEnum;
import com.system.common.dto.PageDTO;
import com.system.common.support.XBeanUtil;
import com.system.common.utils.DateUtils;
import com.system.common.utils.Jwtutils;
import com.system.common.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

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
    @GetMapping(value = "/getLogonUserInfo")
    @ResponseBody
    public ResponseVO<UserVO> getLogonUserInfo(HttpServletRequest request, HttpServletResponse response) {

        Integer userId = Jwtutils.verifyToken(request, response);
        UserDTO user = userService.getUserById(userId);

        UserVO userVO = new UserVO();
        try {
            XBeanUtil.copyProperties(userVO, user, false);
        } catch (Exception e) {
        }
        userVO.setUserName(user.getName());
        userVO.setCreateTime(DateUtils.formatDate(user.getCreateTime()));
        Permission permission = generatePermission(user.getIsRoot());
        userVO.setPermissions(permission);

        return ResponseVO.successResponse(userVO);
    }

    private Permission generatePermission(Integer isRoot) {
        Permission permission = new Permission();
        if (isRoot == YesNoEnum.YES.getValue()) {
            permission.setRole(Role.ROLE_ADMIN);
            permission.setVisit(RouteConstant.MANAGER_ROUTE_IDS);
        } else {
            permission.setRole(Role.ROLE_VISTOR);
            permission.setVisit(RouteConstant.VISTOR_ROUTE_IDS);
        }

        return permission;
    }

    @ApiOperation("获取非管理员用户信息")
    @GetMapping(value = "/getUsers")
    @ResponseBody
    public ResponseVO<PageDTO<UserVO>> getUsers(UserQueryForm form) {
        UserQueryDto queryDto = new UserQueryDto();
        try {
            XBeanUtil.copyProperties(queryDto, form, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null != form.getCreateTime() && form.getCreateTime().size() == 2) {
            queryDto.setBeginTime(form.getCreateTime().get(0));
            queryDto.setEndTime(form.getCreateTime().get(1));
        }

        PageDTO<UserDTO> find = userService.getUsers(queryDto);
        List<UserDTO> findList = find.getList();
        List<UserVO> resList = new ArrayList<>();
        for (UserDTO dto : findList) {
            UserVO vo = new UserVO();
            BeanUtils.copyProperties(dto, vo);
            vo.setUserName(dto.getName());
            vo.setCreateTime(DateUtils.formatDate(dto.getCreateTime()));
            resList.add(vo);
        }


        return ResponseVO.successResponse(new PageDTO<UserVO>(find.getTotal(), resList));
    }

    @ApiOperation("获取用户")
    @GetMapping(value = "getUser/{id}")
    @ResponseBody
    public ResponseVO<UserVO> getUserById(@PathVariable(name = "id") int userId) {

        UserDTO user = userService.getUserById(userId);

        UserVO userVO = new UserVO();
        try {
            XBeanUtil.copyProperties(userVO, user, false);
        } catch (Exception e) {
        }
        userVO.setUserName(user.getName());
        userVO.setCreateTime(DateUtils.formatDate(user.getCreateTime()));
        Permission permission = generatePermission(user.getIsRoot());
        userVO.setPermissions(permission);

        return ResponseVO.successResponse(userVO);
    }

    @PostMapping
    @ApiOperation("添加用户")
    @ResponseBody
    public ResponseVO<Boolean> addUser(@RequestBody UserForm form) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(form, userDTO);
        userDTO.setName(form.getUserName());
        Boolean res = userService.addUser(userDTO);

        return ResponseVO.successResponse(res);
    }

    @ApiOperation("删除用户")
    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public ResponseVO<Integer> deleteUser(@PathVariable(name = "id") Integer id) {
        Integer res = userService.deleteUser(id);

        return ResponseVO.successResponse(res);
    }

    @ApiOperation("批量删除用户")
    @DeleteMapping(value = "/deleteUserBatch")
    @ResponseBody
    public ResponseVO<Integer> deleteUserBatch(@RequestBody List<Integer> userIds) {
        Integer res = userService.deleteUserBatch(userIds);

        return ResponseVO.successResponse(res);
    }

    @PutMapping
    @ApiOperation("更新用户")
    @ResponseBody
    public ResponseVO<Integer> updateUser(@RequestBody UserForm form) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(form, userDTO);
        userDTO.setName(form.getUserName());
        Integer res = userService.updateUser(userDTO);

        return ResponseVO.successResponse(res);
    }


    @PostMapping(value = "/editUser")
    @ApiOperation("编辑用户信息")
    @ResponseBody
    public ResponseVO<Boolean> editUser(@RequestBody UserEditForm form) {

        UserEditDTO dto = new UserEditDTO();
        BeanUtils.copyProperties(form, dto);
        Boolean needToLogout = userService.editUser(dto);

        return ResponseVO.successResponse(needToLogout);
    }

}
