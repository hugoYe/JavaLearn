package com.system.business.channel.controller;

import com.system.business.channel.dto.ChannelDto;
import com.system.business.channel.form.ChannelForm;
import com.system.business.channel.service.ChannelService;
import com.system.business.channel.vo.ChannelVO;
import com.system.common.constants.WebConstants;
import com.system.common.utils.DateUtils;
import com.system.common.vo.ListVO;
import com.system.common.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public ResponseVO<Boolean> deleteChannelBatch(@RequestBody List<String> channelIds) {
        Boolean r = channelService.deleteChannelBatch(channelIds);

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

    @ApiOperation("获取所有渠道")
    @GetMapping(value = "/getAllChannel")
    public ResponseVO<ListVO<ChannelVO>> getAllChannel() {
        ListVO<ChannelVO> list = new ListVO<>();
        List<ChannelDto> findAll = channelService.getChannels();
        List<ChannelVO> resList = new ArrayList<>();
        for (ChannelDto find : findAll) {
            ChannelVO vo = new ChannelVO();
            BeanUtils.copyProperties(find, vo);
            vo.setCreateTime(DateUtils.formatDate(find.getCreateTime()));
            vo.setUpdateTime(DateUtils.formatDate(find.getUpdateTime()));
            resList.add(vo);
        }

        list.setList(resList);
        list.setTotal(resList.size());

        return ResponseVO.successResponse(list);
    }
}
