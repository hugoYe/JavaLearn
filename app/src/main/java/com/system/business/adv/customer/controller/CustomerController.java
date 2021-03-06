package com.system.business.adv.customer.controller;


import com.system.business.adv.customer.dto.CustomerDto;
import com.system.business.adv.customer.dto.CustomerQueryDto;
import com.system.business.adv.customer.form.CustomerForm;
import com.system.business.adv.customer.form.CustomerQueryForm;
import com.system.business.adv.customer.service.CustomerService;
import com.system.business.adv.customer.vo.CustomerVO;
import com.system.common.constants.WebConstants;
import com.system.common.dto.PageDTO;
import com.system.common.support.XBeanUtil;
import com.system.common.utils.DateUtils;
import com.system.common.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(tags = "Customer", description = "客户相关接口")
@RequestMapping(value = WebConstants.API_PREFIX + "/customer")
public class CustomerController {

    @Autowired
    CustomerService customService;

    @ApiOperation("添加客户")
    @PostMapping
    public ResponseVO<Boolean> addCustomer(@RequestBody CustomerForm form) {
        CustomerDto customerDto = new CustomerDto();
        BeanUtils.copyProperties(form, customerDto);
        Boolean r = customService.addCustomer(customerDto);

        return ResponseVO.successResponse(r);
    }


    @ApiOperation("删除客户")
    @DeleteMapping
    public ResponseVO<Boolean> deleteCustomer(@RequestBody String custId) {
        Boolean r = customService.deleteCustomer(custId);

        return ResponseVO.successResponse(r);
    }

    @ApiOperation("更新客户")
    @PutMapping
    public ResponseVO<Boolean> updateCustomer(@RequestBody CustomerForm form) {
        CustomerDto CustomerDto = new CustomerDto();
        BeanUtils.copyProperties(form, CustomerDto);
        Boolean r = customService.updateCustomer(CustomerDto);

        return ResponseVO.successResponse(r);
    }

    @ApiOperation("根据客户id获取客户")
    @GetMapping
    public ResponseVO<CustomerVO> getCustomer(@RequestParam String custId) {
        CustomerDto find = customService.getCustomer(custId);
        CustomerVO res = new CustomerVO();
        BeanUtils.copyProperties(find, res);
        res.setCreateTime(DateUtils.formatDate(find.getCreateTime()));
        res.setUpdateTime(DateUtils.formatDate(find.getUpdateTime()));

        return ResponseVO.successResponse(res);
    }

    @ApiOperation("获取客户列表")
    @GetMapping(value = "/getCustomerList")
    public ResponseVO<PageDTO<CustomerVO>> getCustomerList(CustomerQueryForm form) {
        CustomerQueryDto pageQueryDTO = new CustomerQueryDto();
        try {
            XBeanUtil.copyProperties(pageQueryDTO, form, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        PageDTO<CustomerDto> query = customService.getCustomerList(pageQueryDTO);

        List<CustomerDto> findAll = query.getList();
        List<CustomerVO> resList = new ArrayList<>();
        for (CustomerDto find : findAll) {
            CustomerVO vo = new CustomerVO();
            BeanUtils.copyProperties(find, vo);
            vo.setCreateTime(DateUtils.formatDate(find.getCreateTime()));
            vo.setUpdateTime(DateUtils.formatDate(find.getUpdateTime()));
            resList.add(vo);
        }

        return ResponseVO.successResponse(new PageDTO<CustomerVO>(query.getTotal(), resList));
    }

    @ApiOperation("获取广告主字典")
    @GetMapping(value = "/getCustomerDict")
    public ResponseVO<List<CustomerVO>> getCustomerDict() {
        List<CustomerDto> find = customService.getCustomerDict();
        List<CustomerVO> list = find.stream().map(dto -> {
            CustomerVO vo = new CustomerVO();
            BeanUtils.copyProperties(dto, vo);
            vo.setCreateTime(DateUtils.getFormatDate(dto.getCreateTime()));
            vo.setUpdateTime(DateUtils.getFormatDate(dto.getUpdateTime()));

            return vo;
        }).collect(Collectors.toList());

        return ResponseVO.successResponse(list);
    }
}
