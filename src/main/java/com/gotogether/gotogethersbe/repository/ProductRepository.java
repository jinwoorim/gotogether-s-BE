package com.gotogether.gotogethersbe.repository;

import com.gotogether.gotogethersbe.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryQueryDsl {
    Optional<Product> findById(Long productId);
}
