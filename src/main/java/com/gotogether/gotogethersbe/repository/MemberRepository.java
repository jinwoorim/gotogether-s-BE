package com.gotogether.gotogethersbe.repository;

import com.gotogether.gotogethersbe.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
}
