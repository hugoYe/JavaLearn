package com.system.business.user.service;

import com.system.business.user.dto.ModifyPasswordDTO;
import com.system.business.user.dto.UserDTO;

public interface UserService {

    /**
     * 用户登录
     *
     * @param name     用户名
     * @param password 密码
     * @return UserDTO
     */
    UserDTO login(String name, String password);

    /**
     * 根据用户id获取用户
     *
     * @param userId 用户id
     * @return UserDTO
     */
    UserDTO getUserById(Integer userId);

    /**
     * 添加用户
     *
     * @param userDTO 用户信息
     * @return UserDTO
     */
    UserDTO addUser(UserDTO userDTO);

    /**
     * 编辑用户信息
     *
     * @param userDTO 用户信息
     * @return Integer
     */
    Integer editUser(UserDTO userDTO);

    /**
     * 编辑用户信息
     *
     * @param dto 修改密码信息
     * @return Integer
     */
    Integer modifyPassword(ModifyPasswordDTO dto);

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return Integer
     */
    Integer deleteUser(int id);
}
