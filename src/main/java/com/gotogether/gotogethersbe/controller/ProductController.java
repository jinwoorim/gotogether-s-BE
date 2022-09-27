package com.gotogether.gotogethersbe.controller;


import com.gotogether.gotogethersbe.dto.ProductDto;
import com.gotogether.gotogethersbe.service.ProductService;
import com.gotogether.gotogethersbe.web.api.DefaultRes;
import com.gotogether.gotogethersbe.web.api.ResponseMessage;
import com.gotogether.gotogethersbe.web.api.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 연령대별 상품 추천
    @GetMapping("/ages")
    public DefaultRes<List<ProductDto.ProductResponse>> getAgesProductList(ProductDto.RecommendationRequest request) {
        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_CUSTOM_PRODUCT_LIST, productService.recommendByAges(request));
    }

    // 유형별 상품 추천

    // 골프 상품 추천
    @GetMapping("/golf")
    public DefaultRes<List<ProductDto.ProductResponse>> getGolfProductList() {
        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_CUSTOM_PRODUCT_LIST, productService.recommendGolf());
    }

    // 문화탐방 상품 추천
    @GetMapping("/culture")
    public DefaultRes<List<ProductDto.ProductResponse>> getCultureProductList() {
        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_CUSTOM_PRODUCT_LIST, productService.recommendCulture());
    }

    // 휴양지 상품 추천
    @GetMapping("/healing")
    public DefaultRes<List<ProductDto.ProductResponse>> getHealingProductList() {
        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_CUSTOM_PRODUCT_LIST, productService.recommendHealing());
    }
}
