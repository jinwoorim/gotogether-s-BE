package com.gotogether.gotogethersbe.dto;

import com.gotogether.gotogethersbe.domain.ProductOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProductOptionDto {

    @Getter
    public static class OptionRequest{
        private Long id;
        private String name;
        private String value;
        private long additional;

        public ProductOption toProductOption(){
            return ProductOption.builder()
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

        public OptionResponse(ProductOption productOption){
            this.id = productOption.getId();
            this.name = productOption.getName();
            this.value = productOption.getValue();
            this.additional = productOption.getAdditional();
        }
    }
}
