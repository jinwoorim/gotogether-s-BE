package com.gotogether.gotogethersbe.repository;

import com.gotogether.gotogethersbe.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
