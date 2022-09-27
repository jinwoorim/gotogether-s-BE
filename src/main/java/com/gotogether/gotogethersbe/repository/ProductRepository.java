package com.gotogether.gotogethersbe.repository;

import com.gotogether.gotogethersbe.domain.Product;
import com.gotogether.gotogethersbe.domain.enums.Ages;
import com.gotogether.gotogethersbe.domain.enums.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findRandom();
    List<Product> findCustom();
    List<Product> findByAges(Ages ages);

    // 골프, 문화탐방, 휴양지 추천에 사용
    List<Product> findThemeProducts(Theme theme);

}
