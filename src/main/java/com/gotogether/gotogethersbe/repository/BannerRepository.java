package com.gotogether.gotogethersbe.repository;

import com.gotogether.gotogethersbe.domain.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BannerRepository extends JpaRepository<Banner, Long> {
    List<Banner> findAll();
}
