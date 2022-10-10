package com.gotogether.gotogethersbe.dto;

import com.gotogether.gotogethersbe.domain.Curation;
import com.gotogether.gotogethersbe.domain.enums.*;
import lombok.Data;

public class CurationDto {

    @Data
    public static class CurationRequest{
        private String ages;
        private GenderGroup genderGroup;
        private Companion companion;
        private Religion religion;
        private Theme theme;

        public Curation toCuration(){
            return Curation.builder()
                    .ages(ages)
                    .genderGroup(genderGroup)
                    .companion(companion)
                    .religion(religion)
                    .theme(theme)
                    .build();
        }
    }
}
