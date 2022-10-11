package com.gotogether.gotogethersbe.service;

import com.gotogether.gotogethersbe.dto.BannerDto;
import com.gotogether.gotogethersbe.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BannerService {

    private final BannerRepository bannerRepository;

    @Transactional(readOnly = true)
    public List<BannerDto.BannerResponse> getBanner(){
        return bannerRepository.findAll()
            .stream().map(BannerDto.BannerResponse::of)
            .collect(Collectors.toList());

    }
}
