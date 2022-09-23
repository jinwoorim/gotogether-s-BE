package com.gotogether.gotogethersbe.repository;

import com.gotogether.gotogethersbe.domain.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishRepository extends JpaRepository<Wish, Long> {

    List<Wish> findByMember_id(Long id);
}
