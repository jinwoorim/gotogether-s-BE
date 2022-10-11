package com.gotogether.gotogethersbe.dto;

import com.gotogether.gotogethersbe.domain.Banner;
import com.gotogether.gotogethersbe.domain.Product;
import com.gotogether.gotogethersbe.domain.enums.*;
import lombok.*;

import java.util.List;

public class BannerDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class BannerResponse {
        private Long id;
        private String bannerImage;

        public static BannerResponse of(Banner banner) {
            return BannerResponse.builder()
                    .id(banner.getId())
                    .bannerImage(banner.getBannerImage())

                    .build();
        }
    }
}
