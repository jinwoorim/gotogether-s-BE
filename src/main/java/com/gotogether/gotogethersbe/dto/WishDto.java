package com.gotogether.gotogethersbe.dto;

import com.gotogether.gotogethersbe.domain.Product;
import com.gotogether.gotogethersbe.domain.Wish;
import lombok.Data;

public class WishDto {

    // 찜 하기
    @Data
    public static class WishRequest {

        private Long product_id;
    }

    // 찜 목록 조회
    @Data
    public static class WishListResponse {

        private Product product;

        public WishListResponse(Wish wish) {
            product = wish.getProduct();
        }
    }

    // 찜 선택 삭제
    @Data
    public static class WishDeleteRequest {

        private Long wish_id;
    }
}
