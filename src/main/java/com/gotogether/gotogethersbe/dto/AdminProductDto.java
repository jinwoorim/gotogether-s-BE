package com.gotogether.gotogethersbe.dto;

import com.gotogether.gotogethersbe.domain.enums.*;

public class AdminProductDto {

    public static class ProductRequest{
        private String thumbnail;
        private String productName;
        private long amount;
        private String info;
        private String country;
        private Continent continent;

        //요약 정보
        private String region;
        private String airport;
        private String points;

        //추천 데이터
        private Ages ages;
        private Companion companion;
        private GenderGroup genderGroup;
        private Religion religion;
        private Theme theme;
    }
}
