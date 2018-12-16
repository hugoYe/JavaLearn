package com.system.business.user.service.impl;

import com.system.business.channel.dao.ChannelDao;
import com.system.business.channel.domain.ChannelDomain;
import com.system.business.user.dao.UserDao;
import com.system.business.user.domain.UserDomain;
import com.system.business.user.dto.ModifyPasswordDTO;
import com.system.business.user.dto.UserDTO;
import com.system.business.user.service.UserService;
import com.system.business.userchannel.dao.UserAndChannelDao;
import com.system.business.userchannel.domain.UserAndChannelDomain;
import com.system.common.constants.WebConstants;
import com.system.common.constants.YesNoEnum;
import com.system.common.support.XBeanUtil;
import com.system.common.utils.SHA256Utils;
import com.system.exception.BizException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ChannelDao channelDao;

    @Autowired
    private UserAndChannelDao userAndChannelDao;

    @Value("${application.login.salt:phoenixAd}")
    private String salt;

    @Override
    public UserDTO login(String name, String password) {
        // 校验用户是否存在
        UserDomain exist = userDao.findByNameAndIsDeleted(name, YesNoEnum.NO.getValue());
        if (null == exist) {
            throw new BizException("user.not.exist");
        }

        String prePassword = SHA256Utils.encryptPassword(password, salt);
        if (!prePassword.equals(exist.getPassword())) {
            throw new BizException("user.original.password.error", "20001");
        }

        UserDTO userDTO = new UserDTO();
        try {
            XBeanUtil.copyProperties(userDTO, exist, false);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException();
        }

        return userDTO;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public UserDTO getUserById(Integer userId) {
        // 校验用户是否存在
        UserDomain exist = userDao.findByIdAndIsDeleted(userId, YesNoEnum.NO.getValue());
        if (null == exist) {
            throw new BizException("user.not.exist");
        }

        UserDTO user = new UserDTO();
        try {
            XBeanUtil.copyProperties(user, exist, false);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException();
        }

        return user;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public Boolean addUser(UserDTO userDTO) {
        // 用户名唯一性校验
        UserDomain exist = userDao.findByNameAndIsDeleted(userDTO.getName(), YesNoEnum.NO.getValue());
        if (null != exist) {
            throw new BizException("user.name.exist");
        }

        // 添加用户默认密码
        userDTO.setPassword(WebConstants.DREFAULT_PASSWORD);
        userDTO.setIsDeleted(YesNoEnum.NO.getValue());
        userDTO.setIsRoot(YesNoEnum.NO.getValue());

        UserDomain user = new UserDomain();
        try {
            XBeanUtil.copyProperties(user, userDTO, false);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException();
        }
        if (StringUtils.isNotBlank(userDTO.getPassword())) {
            user.setPassword(SHA256Utils.encryptPassword(userDTO.getPassword(), salt));
        }
        user = userDao.save(user);

        if (userDTO.getChannelName() != null && userDTO.getChannelName().size() > 0) {
            List<UserAndChannelDomain> ucList = new ArrayList<>();
            List<ChannelDomain> findAll = channelDao.queryChannelIdByNames(userDTO.getChannelName());
            for (ChannelDomain domain : findAll) {
                UserAndChannelDomain uc = new UserAndChannelDomain();
                uc.setUserId(user.getId());
                uc.setChannelId(domain.getChannelId());
                uc.setIsDeleted(YesNoEnum.NO.getValue());
                ucList.add(uc);
            }

            userAndChannelDao.save(ucList);
        }

        return true;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public Integer editUser(UserDTO userDTO) {
        // 校验用户是否存在
        UserDomain editUser = userDao.findByIdAndIsDeleted(userDTO.getId(), YesNoEnum.NO.getValue());
        if (null == editUser) {
            throw new BizException("user.not.exist");
        }

        // 校验用户名唯一性
        if (!StringUtils.isEmpty(userDTO.getName())) {
            UserDomain user = userDao.findByNameAndIsDeleted(userDTO.getName(), YesNoEnum.NO.getValue());
            if (null != user && !user.getId().equals(editUser.getId())) {
                throw new BizException("user.name.exist");
            }
        }

        try {
            XBeanUtil.copyProperties(editUser, userDTO, false);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException();
        }
        UserDomain saved = userDao.save(editUser);

        return saved.getId();
    }

    @Override
    public Integer modifyPassword(ModifyPasswordDTO dto) {
        UserDomain user = userDao.findByIdAndIsDeleted(dto.getId(), YesNoEnum.NO.getValue());
        if (null == user) {
            throw new BizException("user.not.exist");
        }

        String prePassword = SHA256Utils.encryptPassword(dto.getPrePassword(), salt);
        if (!prePassword.equals(user.getPassword())) {
            throw new BizException("user.original.password.error");
        }

        user.setPassword(SHA256Utils.encryptPassword(dto.getNewPassword(), salt));
        UserDomain saved = userDao.save(user);

        return saved.getId();
    }

    @Override
    public Integer deleteUser(int id) {
        return null;
    }
}
