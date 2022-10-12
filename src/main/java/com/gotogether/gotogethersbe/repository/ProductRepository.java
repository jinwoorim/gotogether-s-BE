package com.gotogether.gotogethersbe.repository;

import com.gotogether.gotogethersbe.domain.Product;
import com.gotogether.gotogethersbe.domain.enums.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryQueryDsl {
    List<Product> findByProductNameContains(String productName);
    Optional<Product> findById(Long productId);
}
