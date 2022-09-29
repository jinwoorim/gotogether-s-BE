package com.gotogether.gotogethersbe.dto;

import com.gotogether.gotogethersbe.domain.Option;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

public class OptionDto {

    public static class OptionRequest{
        private Long id;
        private String name;
        private String value;
        private Long additional;

        public Option toOption(){
            return Option.builder()
                    .id(id)
                    .name(name)
                    .value(value)
                    .additional(additional)
                    .build();
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class OptionResponse{
        private Long id;
        private String name;
        private String value;
        private Long additional;

        public OptionResponse(Option option){
            this.id = option.getId();
            this.name = option.getName();
            this.value = option.getValue();
            this.additional = option.getAdditional();
        }
    }
}
