package com.gotogether.gotogethersbe.repository;

import com.gotogether.gotogethersbe.domain.enums.*;
import com.gotogether.gotogethersbe.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepositoryQueryDsl {


    List<ProductDto.ProductResponse> findCustom(
            String ages, GenderGroup genderGroup, Companion companion, Religion religion, Theme theme);
    Page<ProductDto.ProductResponse> findAllCategoriesComplex(Pageable pageable, String category);

    Page<ProductDto.ProductResponse> findByProductNameContainsComplex(Pageable pageable, String keyword);
}
