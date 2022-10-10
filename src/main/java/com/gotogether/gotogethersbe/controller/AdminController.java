package com.gotogether.gotogethersbe.controller;

import com.gotogether.gotogethersbe.dto.AdminProductDto;
import com.gotogether.gotogethersbe.service.AdminService;
import com.gotogether.gotogethersbe.web.api.DefaultRes;
import com.gotogether.gotogethersbe.web.api.ResponseMessage;
import com.gotogether.gotogethersbe.web.api.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/admin")
@RestController
public class AdminController {

    private final AdminService adminService;

    //상품 등록
    @PostMapping("/products")
    public DefaultRes insertProduct(@RequestBody AdminProductDto.ProductRequest request){
        adminService.insertProduct(request);
        return DefaultRes.res(StatusCode.OK, ResponseMessage.INSERT_PRODUCT_SUCCESS);
    }

    //아래 로직은 추후 프론트 구현 예정

    //상품 수정
    @PutMapping("/products")
    public DefaultRes updateProduct(@RequestBody AdminProductDto.ProductRequest request){
        adminService.updateProduct(request);
        return DefaultRes.res(StatusCode.OK, ResponseMessage.UPDATE_PRODUCT_SUCCESS);
    }

    //상품 삭제
    @DeleteMapping("/products/{productId}")
    public DefaultRes deleteProduct(@PathVariable Long productId){
        adminService.deleteProduct(productId);
        return DefaultRes.res(StatusCode.OK, ResponseMessage.DELETE_PRODUCT_SUCCESS);
    }

    //상품 리스트 조회
    @GetMapping("/products")
    public DefaultRes<List<AdminProductDto.ProductListResponse>> getProductList(){
        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_PRODUCTS_SUCCESS, adminService.getProductList());
    }

    //상품 상세 조회
    @GetMapping("/products/{productId}")
    public DefaultRes<AdminProductDto.ProductResponse> getProduct(@PathVariable Long productId){
        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_PRODUCT_SUCCESS, adminService.getProduct(productId));
    }
}
