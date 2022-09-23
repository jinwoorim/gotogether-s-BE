package com.gotogether.gotogethersbe.repository;

import com.gotogether.gotogethersbe.domain.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishRepository extends JpaRepository<Wish, Long> {

    List<Wish> findByMember_id(Long id);

    Optional<Wish> findByIdAndMember_id(Long wish_id, Long member_id);
}
