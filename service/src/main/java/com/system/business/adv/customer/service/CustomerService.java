package com.system.business.adv.customer.service;

import com.system.business.adv.customer.dto.CustomerDto;
import com.system.business.adv.customer.dto.CustomerQueryDto;
import com.system.business.user.dto.UserEditDTO;
import com.system.common.dto.PageDTO;

import java.util.List;


public interface CustomerService {

    /**
     * 用户登录
     *
     * @param account  账号
     * @param password 密码
     * @return UserDTO
     */
    CustomerDto login(String account, String password);

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
    PageDTO<CustomerDto> getCustomerList(CustomerQueryDto pageQueryDTO);

    /**
     * 编辑用户信息
     *
     * @param dto 修改信息
     * @return Integer
     */
    Boolean editUser(UserEditDTO dto);

    List<CustomerDto> getCustomerDict();

}
