package com.gotogether.gotogethersbe.dto;

import com.gotogether.gotogethersbe.domain.Product;
import com.gotogether.gotogethersbe.domain.enums.*;
import lombok.*;

public class ProductDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ProductResponse {
        private Long id;

        private String thumbnail;
        private String productName;
        private long amount;
        private String country;
        private Ages ages;
        private GenderGroup genderGruop;
        private Companion companion;
        private Theme theme;


        public static ProductResponse of(Product product) {
            return ProductResponse.builder()
                    .id(product.getId())
                    .thumbnail(product.getThumbnail())
                    .productName(product.getProductName())
                    .amount(product.getAmount())
                    .country(product.getCountry())
                    .ages(product.getAges())
                    .genderGruop(product.getGenderGroup())
                    .companion(product.getCompanion())
                    .theme(product.getTheme())

                    .build();
        }
    }



    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ProductDetailResponse {
        private Long id;

        private String thumbnail;
        private String productName;
        private long amount;
        private String country;
        private String summary;
        private String detail;
        private String info;

        private Ages ages;
        private GenderGroup genderGruop;
        private Companion companion;
        private Religion religion;
        private Theme theme;
        private Continent continent;


        public static ProductDetailResponse of(Product product) {
            return ProductDetailResponse.builder()
                    .id(product.getId())
                    .thumbnail(product.getThumbnail())
                    .productName(product.getProductName())
                    .continent(product.getContinent())
                    .amount(product.getAmount())
                    .country(product.getCountry())
                    .ages(product.getAges())
                    .genderGruop(product.getGenderGroup())
                    .companion(product.getCompanion())
                    .theme(product.getTheme())
                    .religion(product.getReligion())
                    .info(product.getInfo())

                    .build();
        }
    }

    @Getter
    public static class RecommendationRequest {
        private String keyword;
    }

}
