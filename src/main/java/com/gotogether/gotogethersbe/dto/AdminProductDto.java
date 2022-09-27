package com.gotogether.gotogethersbe.dto;

import com.gotogether.gotogethersbe.domain.Option;
import com.gotogether.gotogethersbe.domain.Product;
import com.gotogether.gotogethersbe.domain.enums.*;
import lombok.Getter;

import java.util.List;

public class AdminProductDto {

    @Getter
    public static class ProductRequest{
        private String thumbnail;
        private String productName;
        private long amount;
        private String info;
        private String country;
        private Continent continent;
        private List<OptionDto.OptionRequest> optionDtoList;

        //요약 정보
        private String region;
        private String airport;
        private String points;

        //추천 데이터
        private Ages ages; //다수 선택으로 변경 예정
        private Companion companion;
        private GenderGroup genderGroup;
        private Religion religion;
        private Theme theme;

        public Product toProduct(List<Option> optionList){
            return Product.builder()
                    .thumbnail(thumbnail)
                    .productName(productName)
                    .amount(amount)
                    .info(info)
                    .country(country)
                    .continent(continent)
                    .optionList(optionList)
                    .region(region)
                    .airport(airport)
                    .points(points)
                    .ages(ages)
                    .companion(companion)
                    .genderGroup(genderGroup)
                    .religion(religion)
                    .theme(theme)
                    .build();
        }
    }
}
