package com.gotogether.gotogethersbe.service;

import com.gotogether.gotogethersbe.domain.Member;
import com.gotogether.gotogethersbe.dto.LoginDto;
import com.gotogether.gotogethersbe.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class AuthService {

    private final MemberRepository memberRepository;

    //로그인
    @Transactional(readOnly=true)
    public void login(LoginDto.LoginRequest request){
        Member member = memberRepository.findByEmail(request.getEmail()).orElseThrow();
    }
}
