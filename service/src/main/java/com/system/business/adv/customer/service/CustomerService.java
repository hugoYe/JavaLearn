package com.system.business.adv.customer.service;

import com.system.business.adv.customer.dto.CustomerDto;
import com.system.common.dto.PageDTO;
import com.system.common.dto.PageQueryDTO;


public interface CustomerService {

    /**
     * 添加客户
     */
    Boolean addCustomer(CustomerDto customerDto);

    /**
     * 删除客户，只做逻辑删除，并非真的从数据库删除
     */
    Boolean deleteCustomer(String custId);

    /**
     * 更新客户
     */
    Boolean updateCustomer(CustomerDto customerDto);

    /**
     * 根据客户id获取客户
     */
    CustomerDto getCustomer(String custId);

    /**
     * 获取客户列表
     */
    PageDTO<CustomerDto> getCustomerList(PageQueryDTO pageQueryDTO);

}
