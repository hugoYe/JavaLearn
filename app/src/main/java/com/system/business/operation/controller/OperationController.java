package com.system.business.operation.controller;

import com.system.business.operation.dto.OperationDto;
import com.system.business.operation.dto.OperationQueryDto;
import com.system.business.operation.form.OperationQueryForm;
import com.system.business.operation.service.OperationService;
import com.system.business.operation.vo.OperationVO;
import com.system.common.constants.WebConstants;
import com.system.common.dto.PageDTO;
import com.system.common.support.XBeanUtil;
import com.system.common.utils.DateUtils;
import com.system.common.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(tags = "Income", description = "运营收入相关接口")
@RequestMapping(value = WebConstants.API_PREFIX + "/income")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @ApiOperation("获取收入列表")
    @GetMapping
    public ResponseVO<PageDTO<OperationVO>> getIncomeList(OperationQueryForm form) {
        OperationQueryDto queryDto = new OperationQueryDto();
        try {
            XBeanUtil.copyProperties(queryDto, form, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        PageDTO<OperationDto> queryList = operationService.getIncomeList(queryDto);
        List<OperationDto> qList = queryList.getList();
        List<OperationVO> resList = qList.stream().map(dto -> {
            OperationVO vo = new OperationVO();

            try {
                XBeanUtil.copyProperties(vo, dto, false);
            } catch (Exception e) {
                e.printStackTrace();
            }

            vo.setDate(DateUtils.getFormatDate(dto.getDate()));

            return vo;
        }).collect(Collectors.toList());

        return ResponseVO.successResponse(new PageDTO<OperationVO>(queryList.getTotal(), resList));
    }
}
