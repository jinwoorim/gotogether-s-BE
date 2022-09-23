package com.gotogether.gotogethersbe.dto;

import com.gotogether.gotogethersbe.domain.Product;
import com.gotogether.gotogethersbe.domain.Wish;
import lombok.Data;

public class WishDto {

    @Data
    public static class WishRequest {

        private Long product_id;
    }

    @Data
    public static class WishListResponse {

        private Product product;

        public WishListResponse(Wish wish) {
            product = wish.getProduct();
        }
    }
}
