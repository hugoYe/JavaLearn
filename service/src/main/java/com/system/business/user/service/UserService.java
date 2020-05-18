package com.system.business.user.service;

import com.system.business.user.dto.UserDTO;
import com.system.business.user.dto.UserEditDTO;
import com.system.business.user.dto.UserQueryDto;
import com.system.common.dto.PageDTO;

import java.util.List;

public interface UserService {

    /**
     * 用户登录
     *
     * @param account 账号
     * @param password     密码
     * @return UserDTO
     */
    UserDTO login(String account, String password);

    /**
     * 根据用户id获取用户
     *
     * @param userId 用户id
     * @return UserDTO
     */
    UserDTO getUserByUserId(String userId);

    /**
     * 添加用户
     *
     * @param userDTO 用户信息
     * @return
     */
    Boolean addUser(UserDTO userDTO);

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return Integer
     */
    Integer deleteUser(Integer id);

    Integer deleteUserBatch(List<Integer> userIds);

    Integer updateUser(UserDTO userDTO);


    /**
     * 获取分页用户列表
     *
     * @param queryDto 用户信息
     * @return PageDTO<UserDTO>
     */
    PageDTO<UserDTO> getUsers(UserQueryDto queryDto);

    /**
     * 编辑用户信息
     *
     * @param dto 修改信息
     * @return Integer
     */
    Boolean editUser(UserEditDTO dto);

    List<UserDTO> getUserDict();

}
