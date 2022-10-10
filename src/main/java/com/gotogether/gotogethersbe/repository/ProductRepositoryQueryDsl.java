package com.gotogether.gotogethersbe.repository;

import com.gotogether.gotogethersbe.domain.enums.*;
import com.gotogether.gotogethersbe.dto.ProductDto;

import java.util.List;

public interface ProductRepositoryQueryDsl {


    List<ProductDto.ProductResponse> findCustom(
            String ages, GenderGroup genderGroup, Companion companion, Religion religion, Theme theme);
    List<ProductDto.ProductResponse> findAgesContains(String ages);
    List<ProductDto.ProductResponse> findCompanion(String companion);

}
