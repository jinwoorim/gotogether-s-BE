package com.gotogether.gotogethersbe.dto;

import com.gotogether.gotogethersbe.domain.Option;
import lombok.Builder;

public class OptionDto {

    @Builder
    public static class OptionRequest{
        private String name;
        private String value;
        private Long additional;

        public Option toOption(){
            return Option.builder()
                    .name(name)
                    .value(value)
                    .additional(additional)
                    .build();
        }
    }
}
