package com.gotogether.gotogethersbe.controller;

import com.gotogether.gotogethersbe.dto.BannerDto;
import com.gotogether.gotogethersbe.service.BannerService;
import com.gotogether.gotogethersbe.web.api.DefaultRes;
import com.gotogether.gotogethersbe.web.api.ResponseMessage;
import com.gotogether.gotogethersbe.web.api.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BannerController {

    private final BannerService bannerService;

    @GetMapping("/banner")
    public DefaultRes<List<BannerDto.BannerResponse>> getBanner() {
        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_CUSTOM_BANNER_LIST, bannerService.getBanner());
    }
}
