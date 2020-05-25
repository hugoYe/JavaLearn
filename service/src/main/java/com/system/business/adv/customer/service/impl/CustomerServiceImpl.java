package com.system.business.adv.customer.service.impl;


import com.system.business.adv.customer.dao.CustomerDao;
import com.system.business.adv.customer.domain.CustomerDomain;
import com.system.business.adv.customer.dto.CustomerDto;
import com.system.business.adv.customer.dto.CustomerQueryDto;
import com.system.business.adv.customer.service.CustomerService;
import com.system.business.user.dto.UserEditDTO;
import com.system.common.constants.WebConstants;
import com.system.common.constants.YesNoEnum;
import com.system.common.dto.PageDTO;
import com.system.common.dto.PageQueryDTO;
import com.system.common.support.XBeanUtil;
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

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerDao customerDao;

    @Value("${application.login.salt:phoenixAd}")
    private String salt;


    @Override
    public CustomerDto login(String account, String password) {
        CustomerDomain exist = customerDao.findByCustIdAndIsDeleted(account, YesNoEnum.NO.getValue());
        if (null == exist) {
            throw new BizException("customer.not.exist");
        }

        String prePassword = SHA256Utils.encryptPassword(password, salt);
        if (!prePassword.equals(exist.getPassword())) {
            throw new BizException("user.original.password.error", "20001");
        }

        CustomerDto customerDto = new CustomerDto();
        try {
            XBeanUtil.copyProperties(customerDto, exist, false);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException();
        }

        return customerDto;
    }

    @Override
    public Boolean addCustomer(CustomerDto customerDto) {
        CustomerDomain domain = new CustomerDomain();
        try {
            XBeanUtil.copyProperties(domain, customerDto, false);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException();
        }

        long count = customerDao.count();
        long newId = count + 1;
        String advId;
        if (newId < 100) {
            advId = autoGenericCode(String.valueOf(newId));
        } else {
            advId = String.valueOf(newId);
        }

        domain.setCustId("C" + advId);

        String secretPassword = SHA256Utils.encryptPassword(WebConstants.DREFAULT_CUSTOMER_PASSWORD, salt);
        domain.setPassword(secretPassword);
        domain.setIsDeleted(YesNoEnum.NO.getValue());
        domain.setUserRole("ad_visitor");

        customerDao.save(domain);

        return true;
    }

    private String autoGenericCode(String code) {
        String result = "";
        // 保留code的位数
        result = String.format("%0" + 3 + "d", Integer.parseInt(code));

        return result;
    }

    @Override
    public Boolean deleteCustomer(String custId) {
        CustomerDomain exist = customerDao.findByCustIdAndIsDeleted(custId, YesNoEnum.NO.getValue());
        if (null == exist) {
            throw new BizException("customer.not.exist");
        }

        exist.setIsDeleted(YesNoEnum.YES.getValue());
        exist.setUpdateTime(new Date());

        customerDao.save(exist);

        return true;
    }

    @Override
    public Boolean updateCustomer(CustomerDto customerDto) {
        String custId = customerDto.getCustId();
        CustomerDomain exist = customerDao.findByCustIdAndIsDeleted(custId, YesNoEnum.NO.getValue());
        if (null == exist) {
            throw new BizException("customer.not.exist");
        }

        try {
            XBeanUtil.copyProperties(exist, customerDto, false);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException();
        }

        exist.setUpdateTime(new Date());

        customerDao.save(exist);

        return true;
    }

    @Override
    public CustomerDto getCustomer(String custId) {
        CustomerDomain exist = customerDao.findByCustIdAndIsDeleted(custId, YesNoEnum.NO.getValue());
        if (null == exist) {
            throw new BizException("customer.not.exist");
        }

        CustomerDto customer = new CustomerDto();
        try {
            XBeanUtil.copyProperties(customer, exist, false);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException();
        }
        return customer;
    }

    @Override
    public PageDTO<CustomerDto> getCustomerList(CustomerQueryDto pageQueryDTO) {

        Specification<CustomerDomain> spec = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            //默认查询未删除
            predicates.add(builder.equal(root.get("isDeleted"), YesNoEnum.NO.getValue()));
            if (pageQueryDTO.getCustId() != null) {
                predicates.add(builder.equal(root.get("custId"), pageQueryDTO.getCustId()));
            }

            if (pageQueryDTO.getCustName() != null) {
                predicates.add(builder.equal(root.get("custName"), pageQueryDTO.getCustName()));
            }

            if (pageQueryDTO.getCustType() != null) {
                predicates.add(builder.equal(root.get("custType"), pageQueryDTO.getCustType()));
            }

            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        };

        // 指定查询页面以及排序
        PageRequest request = new PageRequest(pageQueryDTO.getPage() - 1, pageQueryDTO.getPageSize(), Sort.Direction
                .fromString(pageQueryDTO.getOrderDesc()), pageQueryDTO.getOrder());

        Page<CustomerDomain> findList = customerDao.findAll(spec, request);

        PageDTO<CustomerDto> result = PageDTO.of(findList, domain -> {
            CustomerDto dto = new CustomerDto();

            try {
                XBeanUtil.copyProperties(dto, domain, false);
            } catch (Exception e) {
                e.printStackTrace();
                throw new BizException();
            }

            return dto;
        });

        return result;
    }

    @Override
    public Boolean editUser(UserEditDTO dto) {
        CustomerDomain customer = customerDao.findByCustIdAndIsDeleted(dto.getUserId(), YesNoEnum.NO.getValue());
        if (null == customer) {
            throw new BizException("customer.not.exist");
        }

        Boolean needToLogout = false;

        if (StringUtils.isNotBlank(dto.getCurrentPassword())
                && StringUtils.isNotBlank(dto.getNewPassword())
                && StringUtils.isNotBlank(dto.getConfirmPassword())) {

            String curPassword = SHA256Utils.encryptPassword(dto.getCurrentPassword(), salt);
            if (!customer.getPassword().equals(curPassword)) {
                throw new BizException("user.original.password.error");
            }

            if (!dto.getNewPassword().equals(dto.getConfirmPassword())) {
                throw new BizException("user.entered.passwords.differ");
            }

            needToLogout = true;

            customer.setPassword(SHA256Utils.encryptPassword(dto.getNewPassword(), salt));

            customerDao.save(customer);
        }

        return needToLogout;
    }
}
