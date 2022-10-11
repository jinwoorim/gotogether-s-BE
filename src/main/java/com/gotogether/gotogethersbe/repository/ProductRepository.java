package com.gotogether.gotogethersbe.repository;

import com.gotogether.gotogethersbe.domain.Product;
import com.gotogether.gotogethersbe.domain.enums.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryQueryDsl {
    List<Product> findAll();
    @Query(value = "SELECT DISTINCT continent FROM Product", nativeQuery = true)
    List<String> findDistinctContinent();
    List<Product> findByContinent(Continent continent);
    List<Product> findByGenderGroup(GenderGroup genderGroup);
    List<Product> findByTheme(Theme theme);
    List<Product> findByProductNameContains(String productName);


}
