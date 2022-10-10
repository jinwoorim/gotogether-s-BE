package com.gotogether.gotogethersbe.dto;

import com.gotogether.gotogethersbe.domain.Product;
import com.gotogether.gotogethersbe.domain.enums.*;
import lombok.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductDto {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ProductResponse {
        private Long id;
        private String thumbnail;
        private String productName;
        private String basicPrice;
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

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class DetailResponse<E> {
        private Long id;
        private String thumbnail;
        private String productName;
        private String basicPrice;
        private String country;
        private String detail;
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

        public static DetailResponse of(Product product) {
            Map<String, List<ProductOptionDto.OptionResponse>> optionList
                    = product.getProductOptionList()
                    .stream().map(ProductOptionDto.OptionResponse::new)
                    .collect(Collectors.groupingBy(ProductOptionDto.OptionResponse::getName));

            return DetailResponse.builder()
                    .id(product.getId())
                    .thumbnail(product.getThumbnail())
                    .productName(product.getProductName())
                    .basicPrice(product.getBasicPrice())
                    .country(product.getCountry())
                    .ages(product.getAges())
                    .genderGroup(product.getGenderGroup())
                    .companion(product.getCompanion())
                    .theme(product.getTheme())
                    .religion(product.getReligion())
                    .continent(product.getContinent())
                    .info(product.getInfo())
                    .productOptionList(optionList)
                    .build();
        }
    }


    @Getter
    public static class ContinentRequest {
        private Continent continent;
    }
    @Getter
    public static class CompanionRequest {
        private Companion companion;
    }
    @Getter
    public static class GenderGroupRequest {
        private GenderGroup genderGroup;
    }

    @Getter
    public static class ThemeRequest {
        private Theme theme;
    }
    @Getter
    public static class SearchRequest {
        private String keyword;
    }
    @Getter
    public static class DetailRequest {
        private Long productId;
    }


}
