package com.gotogether.gotogethersbe.controller;


import com.gotogether.gotogethersbe.dto.ProductDto;
import com.gotogether.gotogethersbe.service.ProductService;
import com.gotogether.gotogethersbe.web.api.DefaultRes;
import com.gotogether.gotogethersbe.web.api.ResponseMessage;
import com.gotogether.gotogethersbe.web.api.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 설문응답시 맞춤상품
    // 설문미응답시 전체상품 추천
    @GetMapping("/product-list/custom")
    public DefaultRes<Page<ProductDto.ProductResponse>> getCustomProductList(Pageable pageable, HttpServletRequest request) {
        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_CUSTOM_PRODUCT_LIST, productService.recommendByCustom(pageable, request));
    }

    //메인페이지 여행지 종류
    @GetMapping("/continent-list")
    public DefaultRes<List<String>> getContinentList() {
        return DefaultRes.res(StatusCode.OK, ResponseMessage.Get_Continent_Category_List, productService.continentList());
    }

    // 메인페이지, 카테고리별 상품 목록
    @GetMapping("/product-list")
    public DefaultRes<Page<ProductDto.ProductResponse>> getProductsByCategory(Pageable pageable, @RequestParam String category) {
        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_PRODUCT_SEARCH, productService.recommendByCategory(pageable, category));
    }

    // 메인페이지, 카테고리 연령대별 상품 목록
    @GetMapping("/product-list/ages")
    public DefaultRes<Page<ProductDto.ProductResponse>> getProductsByAges(Pageable pageable, @RequestParam String category) {
        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_PRODUCT_SEARCH, productService.recommendByCategory(pageable, category));
    }

    // 메인페이지, 카테고리 연령대별 상품 목록
    @GetMapping("/product-list/continents")
    public DefaultRes<Page<ProductDto.ProductResponse>> getProductsByContinents(Pageable pageable, @RequestParam String category) {
        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_PRODUCT_SEARCH, productService.recommendByCategory(pageable, category));
    }

    // 메인페이지, 카테고리 연령대별 상품 목록
    @GetMapping("/product-list/companion")
    public DefaultRes<Page<ProductDto.ProductResponse>> getProductsByCompanion(Pageable pageable, @RequestParam String category) {
        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_PRODUCT_SEARCH, productService.recommendByCategory(pageable, category));
    }

    // 메인페이지, 카테고리 연령대별 상품 목록
    @GetMapping("/product-list/themes")
    public DefaultRes<Page<ProductDto.ProductResponse>> getProductsByThemes(Pageable pageable, @RequestParam String category) {
        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_PRODUCT_SEARCH, productService.recommendByCategory(pageable, category));
    }

    //메인페이지 카테고리 전체 필터 4개 상품 목록
    @GetMapping("/product-list/all")
    public DefaultRes<Page<ProductDto.ProductResponse>> getProductsByAll(Pageable pageable, @RequestParam String category1, String category2, String category3, String category4) {
        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_PRODUCT_SEARCH, productService.recommendByCategory(pageable, category1, category2, category3, category4));
    }

    // 전체 상품 내 검색
    @GetMapping("/product-search")
    public DefaultRes<Page<ProductDto.ProductResponse>> getSearchProducts(Pageable pageable, @RequestParam String keyword) {
        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_PRODUCT_SEARCH, productService.searchProducts(pageable, keyword));
    }

    //상품 상세정보 보기
    @GetMapping("/product-details/{productId}")
    public DefaultRes<ProductDto.DetailResponse> getProductDetail(@PathVariable Long productId) {
        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_PRODUCT, productService.productDetail(productId));
    }
}
