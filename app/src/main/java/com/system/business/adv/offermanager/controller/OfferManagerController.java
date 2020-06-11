package com.system.business.adv.offermanager.controller;


import com.system.business.adv.offermanager.dto.OfferManagerDto;
import com.system.business.adv.offermanager.dto.OfferManagerQueryDto;
import com.system.business.adv.offermanager.form.OfferManagerForm;
import com.system.business.adv.offermanager.form.OfferManagerQueryForm;
import com.system.business.adv.offermanager.service.OfferManagerService;
import com.system.business.adv.offermanager.vo.OfferManagerVO;
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
@Api(tags = "OfferManager", description = "OfferManager相关接口")
@RequestMapping(value = WebConstants.API_PREFIX + "/offermanager")
public class OfferManagerController {

    @Autowired
    OfferManagerService offerManagerService;

    @ApiOperation("添加offer")
    @PostMapping
    public ResponseVO<Boolean> addOffer(@RequestBody OfferManagerForm form) {
        OfferManagerDto dto = new OfferManagerDto();
        BeanUtils.copyProperties(form, dto);
        Boolean r = offerManagerService.addOffer(dto);

        return ResponseVO.successResponse(r);
    }


    @ApiOperation("删除offer")
    @DeleteMapping
    public ResponseVO<Boolean> deleteOffer(@RequestBody String offerId) {
        Boolean r = offerManagerService.deleteOffer(offerId);

        return ResponseVO.successResponse(r);
    }

    @ApiOperation("更新offer")
    @PutMapping
    public ResponseVO<Boolean> updateOffer(@RequestBody OfferManagerForm form) {
        OfferManagerDto dto = new OfferManagerDto();
        BeanUtils.copyProperties(form, dto);
        Boolean r = offerManagerService.updateOffer(dto);

        return ResponseVO.successResponse(r);
    }

    @ApiOperation("获取offer列表")
    @GetMapping(value = "/getOfferList")
    public ResponseVO<PageDTO<OfferManagerVO>> getOfferList(OfferManagerQueryForm form) {
        OfferManagerQueryDto pageQueryDTO = new OfferManagerQueryDto();
        try {
            XBeanUtil.copyProperties(pageQueryDTO, form, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        PageDTO<OfferManagerDto> query = offerManagerService.getOfferList(pageQueryDTO);

        List<OfferManagerDto> findAll = query.getList();
        List<OfferManagerVO> resList = new ArrayList<>();
        for (OfferManagerDto find : findAll) {
            OfferManagerVO vo = new OfferManagerVO();
            BeanUtils.copyProperties(find, vo);
            vo.setCreateTime(DateUtils.formatDate(find.getCreateTime()));
            vo.setUpdateTime(DateUtils.formatDate(find.getUpdateTime()));
            resList.add(vo);
        }

        return ResponseVO.successResponse(new PageDTO<OfferManagerVO>(query.getTotal(), resList));
    }

    @ApiOperation("获取offer字典")
    @GetMapping(value = "/getOfferDict")
    public ResponseVO<List<OfferManagerVO>> getOfferDict() {
        List<OfferManagerDto> find = offerManagerService.getOfferDict();
        List<OfferManagerVO> list = find.stream().map(dto -> {
            OfferManagerVO vo = new OfferManagerVO();
            BeanUtils.copyProperties(dto, vo);
            vo.setCreateTime(DateUtils.getFormatDate(dto.getCreateTime()));
            vo.setUpdateTime(DateUtils.getFormatDate(dto.getUpdateTime()));

            return vo;
        }).collect(Collectors.toList());

        return ResponseVO.successResponse(list);
    }
}
