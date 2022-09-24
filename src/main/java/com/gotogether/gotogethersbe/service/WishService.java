package com.gotogether.gotogethersbe.service;

import com.gotogether.gotogethersbe.config.util.SecurityUtil;
import com.gotogether.gotogethersbe.domain.Wish;
import com.gotogether.gotogethersbe.dto.WishDto;
import com.gotogether.gotogethersbe.repository.MemberRepository;
import com.gotogether.gotogethersbe.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class WishService {

    private final WishRepository wishRepository;
    private final MemberRepository memberRepository;

    // 찜 하기
    @Transactional
    public void doWish(WishDto.WishRequest request) {

        Wish wish = Wish.builder()
                .product(null)
                .member(memberRepository.findById(SecurityUtil.getCurrentMemberId()).get())
                .build();

        wishRepository.save(wish);
    }

    // 찜 목록 조회
    @Transactional(readOnly = true)
    public List<WishDto.WishListResponse> getWishList() {

        return maptoDto(wishRepository.findByMember_id(SecurityUtil.getCurrentMemberId()));
    }

    private List<WishDto.WishListResponse> maptoDto(List<Wish> wishList) {

        return wishList
                .stream()
                .map(WishDto.WishListResponse::new)
                .toList();
    }

    // 찜 선택 삭제
    @Transactional
    public void deleteWishes(List<WishDto.WishDeleteRequest> list) {

        for(WishDto.WishDeleteRequest wishDeleteRequest : list) {

            Wish wish = wishRepository.findByIdAndMember_id(wishDeleteRequest.getWish_id(), SecurityUtil.getCurrentMemberId())
                    .orElseThrow(NoSuchElementException::new);

            wishRepository.delete(wish);
        }
    }
}
