package com.system.user.controller;

import com.system.common.annotation.NoAuth;
import com.system.common.annotation.NoLogin;
import com.system.common.constants.WebConstants;
import com.system.common.vo.ResponseVO;
import com.system.exception.BizException;
import com.system.permission.Permission;
import com.system.user.dto.ModifyPasswordDTO;
import com.system.user.dto.UserDTO;
import com.system.user.form.LoginForm;
import com.system.user.form.ModifyPasswordForm;
import com.system.user.form.UserAddForm;
import com.system.user.form.UserEditForm;
import com.system.user.service.UserService;
import com.system.user.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public ResponseVO<Integer> login(@RequestBody LoginForm form, HttpServletRequest request) {
        UserDTO userDTO = userService.login(form.getName(), form.getPassword());
        return ResponseVO.successResponse(userDTO.getId());
    }


    @ApiOperation("获取用户信息")
    @GetMapping(value = "/getUserInfo")
    @ResponseBody
    public ResponseVO<UserVO> getUserById(Integer id) {
        UserVO userVO = new UserVO();
        userVO.setId(0);
        userVO.setUsername("yzn");
        userVO.setRealName("yezhennan");
        userVO.setAvatar("");
        Permission permission = new Permission();
        permission.setRole("admin");
        userVO.setPermissions(permission);
        return ResponseVO.successResponse(userVO);
    }


    @PostMapping(value = "/add")
    @ApiOperation("添加用户")
    @ResponseBody
    public ResponseVO<UserVO> addUser(@RequestBody UserAddForm form) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(form, userDTO);
        userService.addUser(userDTO);
        if (null != userDTO.getId()) {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(userDTO, userVO);
            return ResponseVO.successResponse(userVO);
        }

        return ResponseVO.failResponse("AddUser fail!");
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

//    @ApiOperation("根据ID获取用户信息")
//    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", name = "id",
//            dataType = "int", required = true, value = "用户的id", defaultValue = "1")})
//    @ApiResponses({@ApiResponse(code = 400, message = "请求参数没填好"), @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")})
//    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
//    @ResponseBody
//    public User getUserInfo(@RequestParam("id") int id) {
//        if (id == 1) {
//            return userService.findUserByName("yezhennan");
//        }
//
//        return new User();
//    }

//    @RequestMapping(value = "/show", method = RequestMethod.GET)
//    @ResponseBody
//    public String show(@RequestParam(value = "name") String name) {
//        User user = userService.findUserByName(name);
//        if (null != user)
//            return user.getId() + "/" + user.getName() + "/" + user.getPassword();
//        else return "null";
//    }

}
