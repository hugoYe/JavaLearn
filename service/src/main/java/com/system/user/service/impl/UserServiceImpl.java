package com.system.user.service.impl;

import com.system.common.constants.WebConstants;
import com.system.common.constants.YesNoEnum;
import com.system.exception.BizException;
import com.system.user.dao.UserDao;
import com.system.user.domain.UserDomain;
import com.system.user.dto.UserDTO;
import com.system.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        // 用户名唯一性校验
        UserDomain exist = userDao.findByName(userDTO.getName());
        if (null != exist) {
            throw new BizException("user.name.exist");
        }

        // 添加用户默认密码
        userDTO.setPassword(WebConstants.DREFAULT_PASSWORD);
        userDTO.setIsDeleted(YesNoEnum.NO.getValue());
        userDTO.setIsRoot(YesNoEnum.NO.getValue());

        UserDomain user = new UserDomain();
        BeanUtils.copyProperties(userDTO, user);
        user = userDao.save(user);
        userDTO.setId(user.getId());
        return userDTO;
    }

    @Override
    public Integer editUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public Integer deleteUser(int id) {
        return null;
    }
}
