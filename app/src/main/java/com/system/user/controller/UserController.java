package com.system.user.controller;

import com.system.common.vo.ResponseVO;
import com.system.exception.BizException;
import com.system.user.dto.UserDTO;
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

@Controller
@RequestMapping(value = "/user")
@Api(tags = "user", description = "用户相关接口")
public class UserController {

    @Autowired
    private UserService userService;


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

        return ResponseVO.failResponse("addUser fail!");
    }

    @PutMapping(value = "/edit/{id}")
    @ApiOperation("编辑用户")
    @ResponseBody
    public ResponseVO<Integer> editUser(@PathVariable(name = "id") int id, UserEditForm form) {
        if (StringUtils.isEmpty(form.getPassword()) || StringUtils.isEmpty(form.getConfirmPassword())) {
            throw new BizException("none password!");
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

//    @RequestMapping(value = "login", method = RequestMethod.POST)
//    @ResponseBody
//    public String login(@RequestBody User u) {
//        return "success";
//    }
}
