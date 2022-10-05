package com.gotogether.gotogethersbe.repository;

import com.gotogether.gotogethersbe.domain.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<ProductOption, Long> {

    List<ProductOption> findAllByProduct_Id(Long productId);
}
