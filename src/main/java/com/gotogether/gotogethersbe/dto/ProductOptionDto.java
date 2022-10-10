package com.gotogether.gotogethersbe.dto;

import com.gotogether.gotogethersbe.domain.ProductOption;
import lombok.*;



public class ProductOptionDto {

    @Data
    @NoArgsConstructor
    public static class OptionResponse {
        private String name;
        private String additional;
        private String value;
        private Boolean required;
        public OptionResponse(ProductOption productOption) {
            this.name = productOption.getName();
            this.additional = productOption.getAdditional();
            this.value = productOption.getValue();
            this.required = productOption.getRequired();
        }
    }

}
