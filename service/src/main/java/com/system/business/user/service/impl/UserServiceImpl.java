package com.system.business.user.service.impl;

import com.system.business.channel.dao.ChannelDao;
import com.system.business.channel.domain.ChannelDomain;
import com.system.business.user.dao.UserDao;
import com.system.business.user.domain.UserDomain;
import com.system.business.user.dto.ModifyPasswordDTO;
import com.system.business.user.dto.UserDTO;
import com.system.business.user.dto.UserQueryDto;
import com.system.business.user.service.UserService;
import com.system.business.userchannel.dao.UserAndChannelDao;
import com.system.business.userchannel.domain.UserAndChannelDomain;
import com.system.common.constants.WebConstants;
import com.system.common.constants.YesNoEnum;
import com.system.common.dto.PageDTO;
import com.system.common.support.XBeanUtil;
import com.system.common.utils.DateUtils;
import com.system.common.utils.SHA256Utils;
import com.system.exception.BizException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
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
            List<String> findAll = channelDao.queryChannelIdByNames(userDTO.getChannelName());
            for (String id : findAll) {
                UserAndChannelDomain uc = new UserAndChannelDomain();
                uc.setUserId(user.getId());
                uc.setChannelId(id);
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
    public PageDTO<UserDTO> getUsers(UserQueryDto queryDto) {
        Specification<UserDomain> spec = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            //默认查询未删除
            predicates.add(builder.equal(root.get("isDeleted"), YesNoEnum.NO.getValue()));
            // 非管理员用户
            predicates.add(builder.equal(root.get("isRoot"), YesNoEnum.NO.getValue()));

            if (null != queryDto.getName()) {
                predicates.add(builder.equal(root.get("name"), queryDto.getName()));
            }

            if (null != queryDto.getBeginTime() && null != queryDto.getEndTime()) {
                Date beginTime = DateUtils.parse(queryDto.getBeginTime(), DateUtils.DATETIME_FORMAT);
                Date endTime = DateUtils.parse(queryDto.getEndTime(), DateUtils.DATETIME_FORMAT);
                predicates.add(builder.between(root.get("createTime"), beginTime, endTime));
            }

            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        };

        PageRequest request = new PageRequest(queryDto.getPage() - 1, queryDto.getPageSize(), Sort.Direction
                .fromString(queryDto.getOrderDesc()), queryDto.getOrder());

        Page<UserDomain> findList = userDao.findAll(spec, request);

        List<Integer> userIds = new ArrayList<>();
        for (UserDomain d : findList.getContent()) {
            userIds.add(d.getId());
        }

        List<UserAndChannelDomain> findUcList = new ArrayList<>();
        try {
            findUcList = userAndChannelDao.findByUserIds(userIds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<UserAndChannelDomain> ucList = findUcList;

        List<String> channelIds = new ArrayList<>();
        for (UserAndChannelDomain domain : ucList) {
            channelIds.add(domain.getChannelId());
        }

        List<ChannelDomain> findCList = new ArrayList<>();
        try {
            findCList = channelDao.queryChannelByIds(channelIds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<ChannelDomain> cList = findCList;

        PageDTO<UserDTO> result = PageDTO.of(findList, domain -> {
            UserDTO dto = new UserDTO();

            try {
                XBeanUtil.copyProperties(dto, domain, false);
            } catch (Exception e) {
                e.printStackTrace();
                throw new BizException();
            }

            List<String> cIds = new ArrayList<>();
            List<String> cNames = new ArrayList<>();
            for (UserAndChannelDomain d : ucList) {
                if (dto.getId().equals(d.getUserId())) {
                    cIds.add(d.getChannelId());
                    for (ChannelDomain c : cList) {
                        if (c.getChannelId().equals(d.getChannelId())) {
                            cNames.add(c.getChannelName());
                        }
                    }
                }
            }
            dto.setChannelId(cIds);
            dto.setChannelName(cNames);

            return dto;
        });

        return result;
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
