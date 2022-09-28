package com.gotogether.gotogethersbe.repository;

import com.gotogether.gotogethersbe.domain.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long> {

    List<Option> findByProduct_Id(Long productId);
}
