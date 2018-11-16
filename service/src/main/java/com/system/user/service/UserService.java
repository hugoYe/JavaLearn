package com.system.user.service;

import com.system.user.dto.ModifyPasswordDTO;
import com.system.user.dto.UserDTO;

public interface UserService {

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
