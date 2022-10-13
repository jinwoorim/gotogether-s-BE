package com.gotogether.gotogethersbe.controller;


import com.gotogether.gotogethersbe.domain.enums.Continent;
import com.gotogether.gotogethersbe.dto.ProductDto;
import com.gotogether.gotogethersbe.service.ProductService;
import com.gotogether.gotogethersbe.web.api.DefaultRes;
import com.gotogether.gotogethersbe.web.api.ResponseMessage;
import com.gotogether.gotogethersbe.web.api.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 설문응답시 맞춤상품
    // 설문미응답시 전체상품 추천
    @GetMapping("/product-list/custom")
    public DefaultRes<List<ProductDto.ProductResponse>> getCustomProductList(HttpServletRequest request) {
        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_CUSTOM_PRODUCT_LIST, productService.recommendByCustom(request));
    }
    //메인페이지 여행지 종류
    @GetMapping("/continent-list")
    public DefaultRes<List<String>> getContinentList(){
        return DefaultRes.res(StatusCode.OK, ResponseMessage.Get_Continent_Category_List, productService.continentList());
    }

    // 메인페이지, 카테고리 유형별 상품 목록
    @GetMapping("/product-list/categories")
    public DefaultRes<Page<ProductDto.ProductResponse>> getProductsByCategory(Pageable pageable, @RequestParam String category) {
        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_PRODUCT_SEARCH, productService.recommendByCategory(pageable, category));
    }

    // 전체 상품 내 검색
    @GetMapping("/product-search")
    public DefaultRes<Page<ProductDto.ProductResponse>> getSearchProducts(Pageable pageable, @RequestParam String keyword) {
        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_PRODUCT_SEARCH, productService.searchProducts(pageable, keyword));
    }
    // 상품 상세정보 보기
    @GetMapping("/product-details/{productId}")
    public DefaultRes<List<ProductDto.DetailResponse>> getProductDetail(@PathVariable Long productId) {
        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_PRODUCT, productService.productDetail(productId));
    }
}
