package com.gotogether.gotogethersbe.repository;

import com.gotogether.gotogethersbe.domain.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface WishRepository extends JpaRepository<Wish, Long> {

    Optional<Wish> findByProduct_id(Long id);

    List<Wish> findByMember_idOrderByIdDesc(Long id);

    @Modifying(clearAutomatically = true, flushAutomatically = true) // 기존 delete는 조회를하고 delete하는 두번의 쿼리가 날라가지만,
    @Query("delete from Wish u where u.id in ?1")                    // bulk delete를 해주게되면 쿼리는 한번 날라가지만 영속성 컨텍스트와 DB의 데이터의 싱크가 맞지않게 되므로
    void deleteWishes(List<Long> list);                              // @Modifying(clearAutomatically = true, flushAutomatically = true)를 해주어
                                                                     // 연산 직 후 영속성 컨텍스트를 clear하고 DB에 flush 할것을 명시해야한다



}
