package com.gotogether.gotogethersbe.repository;

import com.gotogether.gotogethersbe.domain.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WishRepository extends JpaRepository<Wish, Long> {

    List<Wish> findByMember_id(Long id);

  //  Optional<Wish> findByIdAndMember_id(Long wish_id, Long member_id);
    @Modifying
    @Query("delete from Wish u where u.id in ?1 and u.member.id in ?2")
    void deleteWishWithMemberId(List<Long> list, Long member_id);
}
