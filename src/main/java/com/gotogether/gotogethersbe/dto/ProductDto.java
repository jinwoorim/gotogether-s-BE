package com.gotogether.gotogethersbe.dto;

import com.gotogether.gotogethersbe.domain.Product;
import com.gotogether.gotogethersbe.domain.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

public class ProductDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ProductResponse {
        private Long id;
        private String thumbnail;
        private String productName;
        private long basicPrice;
        private String country;
        private String ages;
        private GenderGroup genderGroup;
        private Companion companion;
        private Theme theme;

        public static ProductResponse of(Product product) {
            return ProductResponse.builder()
                    .id(product.getId())
                    .thumbnail(product.getThumbnail())
                    .productName(product.getProductName())
                    .basicPrice(product.getBasicPrice())
                    .country(product.getCountry())
                    .ages(product.getAges())
                    .genderGroup(product.getGenderGroup())
                    .companion(product.getCompanion())
                    .theme(product.getTheme())

                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class DetailResponse {
        private Long id;
        private String thumbnail;
        private String productName;
        private long basicPrice;
        private String country;
        private String region;
        private String points;
        private String airport;

        private String info;
        private String ages;
        private GenderGroup genderGroup;
        private Companion companion;
        private Religion religion;
        private Theme theme;
        private Continent continent;

        private Map<String, List<ProductOptionDto.OptionResponse>> productOptionList;

        public static DetailResponse of(Product product, Map<String, List<ProductOptionDto.OptionResponse>> productOptionList) {

            return DetailResponse.builder()
                    .id(product.getId())
                    .thumbnail(product.getThumbnail())
                    .productName(product.getProductName())
                    .basicPrice(product.getBasicPrice())
                    .country(product.getCountry())
                    .region(product.getRegion())
                    .points(product.getPoints())
                    .airport(product.getAirport())
                    .ages(product.getAges())
                    .genderGroup(product.getGenderGroup())
                    .companion(product.getCompanion())
                    .theme(product.getTheme())
                    .religion(product.getReligion())
                    .continent(product.getContinent())
                    .info(product.getInfo())
                    .productOptionList(productOptionList)
                    .build();
        }
    }
}
