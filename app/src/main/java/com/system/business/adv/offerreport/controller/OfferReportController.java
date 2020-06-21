package com.system.business.adv.offerreport.controller;

import com.system.business.adv.offerreport.form.OfferReportQueryForm;
import com.system.business.adv.offerreport.vo.OfferReportVO;
import com.system.common.constants.WebConstants;
import com.system.common.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "OfferReport", description = "offer report相关接口")
@RequestMapping(value = WebConstants.API_PREFIX + "/offerReport")
public class OfferReportController {


    @ApiOperation("获取的offer数据列表")
    @GetMapping(value = "/getOfferReportLists")
    public ResponseVO<List<OfferReportVO>> getOfferReportLists(OfferReportQueryForm form) {
        List<OfferReportVO> list = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            OfferReportVO vo = new OfferReportVO();
            vo.setDate("2020-5-12");
            vo.setGrossClicks("12345");
            vo.setUniqueClicks("12345");
            vo.setConversions("502353");
            vo.setRevenue(124124);
            vo.setCr("2342");
            vo.setEcpc("234");
            list.add(vo);
        }


        return ResponseVO.successResponse(list);
    }


}
