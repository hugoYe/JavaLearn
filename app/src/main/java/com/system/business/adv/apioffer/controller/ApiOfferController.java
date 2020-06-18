package com.system.business.adv.apioffer.controller;

import com.system.business.adv.apioffer.form.ApiOfferQueryForm;
import com.system.business.adv.apioffer.vo.ApiOfferVO;
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
@Api(tags = "ApiOffer", description = "apioffer相关接口")
@RequestMapping(value = WebConstants.API_PREFIX + "/apiOffer")
public class ApiOfferController {


    @ApiOperation("获取广告主的offer")
    @GetMapping(value = "/getApiOffers")
    public ResponseVO<List<ApiOfferVO>> getApiOffers(ApiOfferQueryForm form) {
        List<ApiOfferVO> list = new ArrayList<>();

        if (form.getAdvertiser() != null) {
            for (int i = 0; i < 30; i++) {
                ApiOfferVO vo = new ApiOfferVO();
                vo.setAdvertiser("A00" + i);
                vo.setAdvOfferId("10000" + i);
                vo.setOfferName("offer" + i);
                vo.setCountry("China" + i);
                vo.setCarrier("Union" + i);
                vo.setPayoutIn(234.06);
                vo.setCap(10 + i);
                vo.setProcess("1click");
                vo.setDescription("falksfja;fl");
                vo.setCategory(1);
                vo.setCurrencyUnit("dollar");
                vo.setOfferType("cpa");
                vo.setOfferUrl("http://baidu.com");
                vo.setPayoutOut(2342.234);
                vo.setPostbackUrl("http://baidu.com");
                vo.setPreviewUrl("http://baidu.com");
                vo.setTimeZone("CMT+8");
                vo.setStatus(1);
                vo.setProportion(100);
                list.add(vo);
            }
        }

        return ResponseVO.successResponse(list);
    }


}
