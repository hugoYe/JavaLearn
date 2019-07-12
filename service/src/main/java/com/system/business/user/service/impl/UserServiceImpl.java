package com.system.business.user.service.impl;

import com.system.business.channel.dao.ChannelDao;
import com.system.business.channel.domain.ChannelDomain;
import com.system.business.user.dao.UserDao;
import com.system.business.user.domain.UserDomain;
import com.system.business.user.dto.UserDTO;
import com.system.business.user.dto.UserEditDTO;
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
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    public UserDTO login(String nameOrUserId, String password) {
        // 校验用户是否存在
        UserDomain exist = userDao.findByNameAndIsDeleted(nameOrUserId, YesNoEnum.NO.getValue());
        if (null == exist) {
            exist = userDao.findByUserIdAndIsDeleted(nameOrUserId, YesNoEnum.NO.getValue());
            if (null == exist) {
                throw new BizException("user.not.exist");
            }
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

        if (user.getIsRoot() == YesNoEnum.NO.getValue()) {
            List<UserAndChannelDomain> ucList = userAndChannelDao.findByUserId(userId);
            List<String> channelIds = ucList.stream().map(UserAndChannelDomain::getChannelId).collect(Collectors.toList());
            user.setChannelId(channelIds);
            List<ChannelDomain> cList = channelDao.queryChannelByIds(channelIds);
            List<String> channelNames = cList.stream().map(ChannelDomain::getChannelName).collect(Collectors.toList());
            user.setChannelName(channelNames);
        }

        return user;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public Boolean addUser(UserDTO userDTO) {
        // 用户名唯一性校验
//        UserDomain exist = userDao.findByNameAndIsDeleted(userDTO.getName(), YesNoEnum.NO.getValue());
//        if (null != exist) {
//            throw new BizException("user.name.exist");
//        }

        long count = userDao.count();
        long newId = count + 1;
        String userId;
        if (newId < 100) {
            userId = autoGenericCode(String.valueOf(newId));
        } else {
            userId = String.valueOf(newId);
        }
        userDTO.setUserId("wy" + userId);

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

        if (userDTO.getChannelId() != null && userDTO.getChannelId().size() > 0) {
            List<UserAndChannelDomain> ucList = generateUserAndChannelDomainList(user.getId(), userDTO.getChannelId());

            userAndChannelDao.save(ucList);
        }

        return true;
    }

    private String autoGenericCode(String code) {
        String result = "";
        // 保留code的位数
        result = String.format("%0" + 3 + "d", Integer.parseInt(code));

        return result;
    }


    private List<UserAndChannelDomain> generateUserAndChannelDomainList(Integer userID, List<String> channelIds) {
        List<UserAndChannelDomain> ucList = new ArrayList<>();
        for (String cId : channelIds) {
            UserAndChannelDomain uc = new UserAndChannelDomain();
            uc.setUserId(userID);
            uc.setChannelId(cId);
            uc.setIsDeleted(YesNoEnum.NO.getValue());
            ucList.add(uc);
        }

        return ucList;
    }

    @Override
    public Integer deleteUser(Integer id) {
        // 校验用户是否存在
        UserDomain exist = userDao.findByIdAndIsDeleted(id, YesNoEnum.NO.getValue());
        if (null == exist) {
            throw new BizException("user.not.exist");
        }

        exist.setIsDeleted(YesNoEnum.YES.getValue());
        userDao.save(exist);

        userAndChannelDao.deleteUserAndChannel(id);

        return id;
    }

    @Override
    public Integer deleteUserBatch(List<Integer> userIds) {
        Integer res = userDao.deleteUserBatch(userIds);
        userAndChannelDao.deleteUserAndChannelBatch(userIds);

        return res;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public Integer updateUser(UserDTO userDTO) {
        // 校验用户是否存在
        UserDomain exist = userDao.findByNameAndIsDeleted(userDTO.getName(), YesNoEnum.NO.getValue());
        if (null == exist) {
            throw new BizException("user.not.exist");
        }

        try {
            XBeanUtil.copyProperties(exist, userDTO, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        userDao.save(exist);

        List<UserAndChannelDomain> all = userAndChannelDao.findAllByUserId(userDTO.getId());
        Map<String, UserAndChannelDomain> allMap = all.stream().collect(Collectors.toMap(UserAndChannelDomain::getChannelId, Function.identity()));
        List<String> selectChannelIds = userDTO.getChannelId();
        List<String> addedChannelIds = new ArrayList<>();
        List<String> updateChannelIds = new ArrayList<>();
        List<String> deletedChannelIds = new ArrayList<>();

        for (String cId : selectChannelIds) {
            if (allMap.containsKey(cId)) {
                UserAndChannelDomain uc = allMap.get(cId);
                if (uc.getIsDeleted() == YesNoEnum.YES.getValue()) {
                    updateChannelIds.add(cId);
                }
            } else {
                addedChannelIds.add(cId);
            }
        }

        for (UserAndChannelDomain domain : all) {
            if (!selectChannelIds.contains(domain.getChannelId()) && domain.getIsDeleted() == YesNoEnum.NO.getValue()) {
                deletedChannelIds.add(domain.getChannelId());
            }
        }


        Integer u = 0;
        if (updateChannelIds.size() > 0) {
            u = userAndChannelDao.updateUserAndChannel(exist.getId(), updateChannelIds, YesNoEnum.NO.getValue());
        }

        Integer d = 0;
        if (deletedChannelIds.size() > 0) {
            d = userAndChannelDao.updateUserAndChannel(exist.getId(), deletedChannelIds, YesNoEnum.YES.getValue());
        }

        Integer a = addedChannelIds.size();
        if (a > 0) {
            List<UserAndChannelDomain> ucList = generateUserAndChannelDomainList(exist.getId(), addedChannelIds);

            userAndChannelDao.save(ucList);
        }

        Integer res = u + d + a;

        return res;
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
    public Boolean editUser(UserEditDTO dto) {
        UserDomain user = userDao.findByIdAndIsDeleted(dto.getId(), YesNoEnum.NO.getValue());
        if (null == user) {
            throw new BizException("user.not.exist");
        }

        try {
            XBeanUtil.copyProperties(user, dto, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Boolean needToLogout = false;

        if (StringUtils.isNotBlank(dto.getCurrentPassword())
                && StringUtils.isNotBlank(dto.getNewPassword())
                && StringUtils.isNotBlank(dto.getConfirmPassword())) {

            String curPassword = SHA256Utils.encryptPassword(dto.getCurrentPassword(), salt);
            if (!user.getPassword().equals(curPassword)) {
                throw new BizException("user.original.password.error");
            }

            if (!dto.getNewPassword().equals(dto.getConfirmPassword())) {
                throw new BizException("user.entered.passwords.differ");
            }

            needToLogout = true;

            user.setPassword(SHA256Utils.encryptPassword(dto.getNewPassword(), salt));
        }

        userDao.save(user);

        return needToLogout;
    }

    @Override
    public List<UserDTO> getUserDict() {
        List<UserDomain> find = userDao.findAllByIsDeletedAndIsRoot(YesNoEnum.NO.getValue(), YesNoEnum.NO.getValue());
        List<UserDTO> res = find.stream().map(userDomain -> {
            UserDTO dto = new UserDTO();
            try {
                XBeanUtil.copyProperties(dto, userDomain, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return dto;
        }).collect(Collectors.toList());

        return res;
    }
}
