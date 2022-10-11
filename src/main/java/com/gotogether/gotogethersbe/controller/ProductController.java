package com.gotogether.gotogethersbe.controller;


import com.gotogether.gotogethersbe.dto.ProductDto;
import com.gotogether.gotogethersbe.service.ProductService;
import com.gotogether.gotogethersbe.web.api.DefaultRes;
import com.gotogether.gotogethersbe.web.api.ResponseMessage;
import com.gotogether.gotogethersbe.web.api.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 설문응답시 맞춤상품
    // 설문미응답시 전체상품 추천
    @GetMapping("/recommended-custom")
    public DefaultRes<List<ProductDto.ProductResponse>> getCustomProductList(HttpServletRequest request) {
        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_CUSTOM_PRODUCT_LIST, productService.recommendByCustom(request));
    }
    //메인페이지 여행지 종류
    @GetMapping("/recommended-continent")
    public DefaultRes<List<String>> getContinentList(){
        return DefaultRes.res(StatusCode.OK, ResponseMessage.Get_Continent_Category_List, productService.continentCategory());
    }
    // 메인페이지, 카테고리 연령대별 상품 목록
    @GetMapping("/ages")
    public DefaultRes<List<ProductDto.ProductResponse>> getProductsInAges(Pageable pageable, @RequestParam String ages) {
        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_PRODUCT_SEARCH, productService.recommendByAges(ages));
    }
    // 메인페이지, 카테고리 유형별 상품 목록
    @GetMapping("/companion")
    public DefaultRes<List<ProductDto.ProductResponse>> getProductsInCompanion(Pageable pageable, @RequestParam String companion) {
        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_PRODUCT_SEARCH, productService.recommendByCompanion(companion));
    }
//    @GetMapping("/companion")
//    public DefaultRes<List<ProductDto.ProductResponse>> getProductsInCompanion(@RequestBody ProductDto.CompanionRequest request) {
//        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_PRODUCT_SEARCH, productService.recommendByCompanion(request));
//    }
    // 메인페이지, 카테고리 유형별 상품 목록
    @GetMapping("/gendergroup")
    public DefaultRes<List<ProductDto.ProductResponse>> getProductsInGenderGroup(@RequestBody ProductDto.GenderGroupRequest request) {
        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_PRODUCT_SEARCH, productService.recommendByGenderGroup(request));
    }
    // 골프 상품 추천
    @GetMapping("/theme-golf")
    public DefaultRes<List<ProductDto.ProductResponse>> getGolfProducts() {
        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_PRODUCT_THEME_GOLF, productService.recommendGolf());
    }
    // 문화탐방 상품 추천
    @GetMapping("/theme-culture")
    public DefaultRes<List<ProductDto.ProductResponse>> getCultureProducts() {
        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_PRODUCT_CATEGORY_CULTURE, productService.recommendCulture());
    }
    // 휴양지 상품 추천
    @GetMapping("/theme-healing")
    public DefaultRes<List<ProductDto.ProductResponse>> getHealingProducts() {
        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_PRODUCT_THEME_HEALING, productService.recommendHealing());
    }
    // 전체 상품 내 검색
    @GetMapping("/search")
    public DefaultRes<List<ProductDto.ProductResponse>> getSearchProducts(@RequestBody ProductDto.SearchRequest request) {
        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_PRODUCT_SEARCH, productService.searchProducts(request));
    }
    // 전체 상품 목록
    @GetMapping("/all")
    public DefaultRes<List<ProductDto.ProductResponse>> getAllProducts() {
        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_PRODUCT_SEARCH, productService.allProducts());
    }
    // 카테고리 국가별 상품 목록
    @GetMapping("/continent")
    public DefaultRes<List<ProductDto.ProductResponse>> getProductsInContinent(@RequestBody ProductDto.ContinentRequest request) {
        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_PRODUCT_SEARCH, productService.recommendByContinent(request));
    }
    // 카테고리 테마별 상품 목록
    @GetMapping("/theme")
    public DefaultRes<List<ProductDto.ProductResponse>> getProductsInTheme(@RequestBody ProductDto.ThemeRequest request) {
        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_PRODUCT_SEARCH, productService.recommendedByTheme(request));
    }
    // 상품 상세정보 보기
    @GetMapping("/details")
    public DefaultRes<List<ProductDto.DetailResponse>> getProductDetail(@RequestBody ProductDto.DetailRequest request) {
        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_PRODUCT, productService.productDetail(request));
    }
}
