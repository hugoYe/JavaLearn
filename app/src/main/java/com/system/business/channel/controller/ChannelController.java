package com.system.business.channel.controller;

import com.system.business.channel.dto.ChannelDto;
import com.system.business.channel.form.ChannelForm;
import com.system.business.channel.service.ChannelService;
import com.system.business.channel.vo.ChannelVO;
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
import java.util.stream.Collectors;

@RestController
@Api(tags = "Channel", description = "渠道管理相关接口")
@RequestMapping(value = WebConstants.API_PREFIX + "/channel")
public class ChannelController {

    @Autowired
    ChannelService channelService;

    @ApiOperation("添加渠道")
    @PostMapping
    public ResponseVO<Boolean> addChannel(@RequestBody ChannelForm form) {
        ChannelDto channelDto = new ChannelDto();
        BeanUtils.copyProperties(form, channelDto);
        Boolean r = channelService.addChannel(channelDto);

        return ResponseVO.successResponse(r);
    }


    @ApiOperation("删除渠道")
    @DeleteMapping
    public ResponseVO<Boolean> deleteChannel(@RequestBody String channelId) {
        Boolean r = channelService.deleteChannel(channelId);

        return ResponseVO.successResponse(r);
    }


    @ApiOperation("批量删除渠道")
    @DeleteMapping(value = "/deleteChannelBatch")
    public ResponseVO<Integer> deleteChannelBatch(@RequestBody List<String> channelIds) {
        Integer r = channelService.deleteChannelBatch(channelIds);

        return ResponseVO.successResponse(r);
    }

    @ApiOperation("更新渠道内容")
    @PutMapping
    public ResponseVO<Boolean> updateChannel(@RequestBody ChannelForm form) {
        ChannelDto channelDto = new ChannelDto();
        BeanUtils.copyProperties(form, channelDto);
        Boolean r = channelService.updateChannel(channelDto);

        return ResponseVO.successResponse(r);
    }

    @ApiOperation("根据渠道id获取渠道")
    @GetMapping
    public ResponseVO<ChannelVO> getChannel(@RequestParam String channelId) {
        ChannelDto find = channelService.getChannel(channelId);
        ChannelVO res = new ChannelVO();
        BeanUtils.copyProperties(find, res);
        res.setCreateTime(DateUtils.formatDate(find.getCreateTime()));
        res.setUpdateTime(DateUtils.formatDate(find.getUpdateTime()));

        return ResponseVO.successResponse(res);
    }

    @ApiOperation("获取渠道列表")
    @GetMapping(value = "/getChannels")
    public ResponseVO<PageDTO<ChannelVO>> getChannels(PageForm form) {
        PageQueryDTO pageQueryDTO = new PageQueryDTO();
        try {
            XBeanUtil.copyProperties(pageQueryDTO, form, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        PageDTO<ChannelDto> query = channelService.getChannels(pageQueryDTO);

        List<ChannelDto> findAll = query.getList();
        List<ChannelVO> resList = new ArrayList<>();
        for (ChannelDto find : findAll) {
            ChannelVO vo = new ChannelVO();
            BeanUtils.copyProperties(find, vo);
            vo.setCreateTime(DateUtils.formatDate(find.getCreateTime()));
            vo.setUpdateTime(DateUtils.formatDate(find.getUpdateTime()));
            resList.add(vo);
        }

        return ResponseVO.successResponse(new PageDTO<ChannelVO>(query.getTotal(), resList));
    }

    @ApiOperation("获取渠道字典")
    @GetMapping(value = "/getChannelDict")
    public ResponseVO<List<ChannelVO>> getChannelDict() {
        List<ChannelDto> find = channelService.getChannelDict();
        List<ChannelVO> list = find.stream().map(dto -> {
            ChannelVO vo = new ChannelVO();
            BeanUtils.copyProperties(dto, vo);
            vo.setCreateTime(DateUtils.getFormatDate(dto.getCreateTime()));
            vo.setUpdateTime(DateUtils.getFormatDate(dto.getUpdateTime()));

            return vo;
        }).collect(Collectors.toList());

        return ResponseVO.successResponse(list);
    }
}
