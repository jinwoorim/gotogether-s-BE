package com.gotogether.gotogethersbe.repository;

import com.gotogether.gotogethersbe.domain.Product;
import com.gotogether.gotogethersbe.domain.enums.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryQueryDsl {
    @Query(value = "select distinct p from Product p INNER JOIN ProductOption o on p.id = o.product.id")
    Optional<Product> findByIdWithProductOption(Long productId);
}
