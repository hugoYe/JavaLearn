package com.system.business.adv.customer.service.impl;


import com.system.business.adv.customer.dao.CustomerDao;
import com.system.business.adv.customer.domain.CustomerDomain;
import com.system.business.adv.customer.dto.CustomerDto;
import com.system.business.adv.customer.dto.CustomerQueryDto;
import com.system.business.adv.customer.service.CustomerService;
import com.system.common.constants.YesNoEnum;
import com.system.common.dto.PageDTO;
import com.system.common.dto.PageQueryDTO;
import com.system.common.support.XBeanUtil;
import com.system.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
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
        return null;
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
}
