package com.gotogether.gotogethersbe.repository;

import com.gotogether.gotogethersbe.domain.enums.Companion;
import com.gotogether.gotogethersbe.domain.enums.GenderGroup;
import com.gotogether.gotogethersbe.domain.enums.Religion;
import com.gotogether.gotogethersbe.domain.enums.Theme;
import com.gotogether.gotogethersbe.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryQueryDsl {


    Page<ProductDto.ProductResponse> findCustomComplex(Pageable pageable, String ages, GenderGroup genderGroup,
                                                       Companion companion, Religion religion, Theme theme);

    Page<ProductDto.ProductResponse> findAllCategoriesComplex(Pageable pageable, String category);

    Page<ProductDto.ProductResponse> findAllCategoriesComplex(Pageable pageable, String category1, String category2, String category3, String category4);

    Page<ProductDto.ProductResponse> findByProductNameContainsComplex(Pageable pageable, String keyword);
}
