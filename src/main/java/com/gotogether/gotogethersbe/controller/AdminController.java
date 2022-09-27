package com.gotogether.gotogethersbe.controller;

import com.gotogether.gotogethersbe.service.AdminService;
import com.gotogether.gotogethersbe.web.api.DefaultRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/admin")
@RestController
public class AdminController {

    private final AdminService adminService;

    //상품 등록
    @PostMapping("/products")
    public DefaultRes insertProduct(@RequestBody ){

    }

    //상품 수정
    @PutMapping("/products")
    public DefaultRes updateProduct(){

    }

    //상품 삭제
    @DeleteMapping("/products/{productId}")
    public DefaultRes deleteProduct(){

    }

    //상품 리스트 조회
    @GetMapping("/products")
    public DefaultRes getProductList(){

    }

    //상품 상세 조회
    @GetMapping("/products/{productId}")
    public DefaultRes getProduct(){

    }

}
