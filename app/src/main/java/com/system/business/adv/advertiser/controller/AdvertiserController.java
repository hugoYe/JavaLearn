package com.system.business.adv.advertiser.controller;

import com.system.business.adv.advertiser.dto.AdvertiserDto;
import com.system.business.adv.advertiser.form.AdvertiserForm;
import com.system.business.adv.advertiser.service.AdvertiserService;
import com.system.business.adv.advertiser.vo.AdvertiserVO;
import com.system.common.constants.WebConstants;
import com.system.common.dto.PageDTO;
import com.system.common.dto.PageQueryDTO;
import com.system.common.form.PageForm;
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

@RestController
@Api(tags = "Advertiser", description = "广告主相关接口")
@RequestMapping(value = WebConstants.API_PREFIX + "/advertiser")
public class AdvertiserController {

    @Autowired
    AdvertiserService advertiserService;

    @ApiOperation("添加广告主")
    @PostMapping
    public ResponseVO<Boolean> addAdvertiser(@RequestBody AdvertiserForm form) {
        AdvertiserDto advertiserDto = new AdvertiserDto();
        BeanUtils.copyProperties(form, advertiserDto);
        Boolean r = advertiserService.addAdvertiser(advertiserDto);

        return ResponseVO.successResponse(r);
    }


    @ApiOperation("删除广告主")
    @DeleteMapping
    public ResponseVO<Boolean> deleteAdvertiser(@RequestBody String advId) {
        Boolean r = advertiserService.deleteAdvertiser(advId);

        return ResponseVO.successResponse(r);
    }

    @ApiOperation("更新广告主")
    @PutMapping
    public ResponseVO<Boolean> updateAdvertiser(@RequestBody AdvertiserForm form) {
        AdvertiserDto advertiserDto = new AdvertiserDto();
        BeanUtils.copyProperties(form, advertiserDto);
        Boolean r = advertiserService.updateAdvertiser(advertiserDto);

        return ResponseVO.successResponse(r);
    }

    @ApiOperation("根据广告主id获取广告主")
    @GetMapping
    public ResponseVO<AdvertiserVO> getAdvertiser(@RequestParam String advId) {
        AdvertiserDto find = advertiserService.getAdvertiser(advId);
        AdvertiserVO res = new AdvertiserVO();
        BeanUtils.copyProperties(find, res);
        res.setCreateTime(DateUtils.formatDate(find.getCreateTime()));
        res.setUpdateTime(DateUtils.formatDate(find.getUpdateTime()));

        return ResponseVO.successResponse(res);
    }

    @ApiOperation("获取广告主列表")
    public ResponseVO<PageDTO<AdvertiserVO>> getAdvertiserList(PageForm form) {
        PageQueryDTO pageQueryDTO = new PageQueryDTO();
        try {
            XBeanUtil.copyProperties(pageQueryDTO, form, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        PageDTO<AdvertiserDto> query = advertiserService.getAdvertiserList(pageQueryDTO);

        List<AdvertiserDto> findAll = query.getList();
        List<AdvertiserVO> resList = new ArrayList<>();
        for (AdvertiserDto find : findAll) {
            AdvertiserVO vo = new AdvertiserVO();
            BeanUtils.copyProperties(find, vo);
            vo.setCreateTime(DateUtils.formatDate(find.getCreateTime()));
            vo.setUpdateTime(DateUtils.formatDate(find.getUpdateTime()));
            resList.add(vo);
        }

        return ResponseVO.successResponse(new PageDTO<AdvertiserVO>(query.getTotal(), resList));
    }

}
