package com.gotogether.gotogethersbe.service;

import com.gotogether.gotogethersbe.domain.Member;
import com.gotogether.gotogethersbe.dto.LoginDto;
import com.gotogether.gotogethersbe.repository.MemberRepository;
import com.gotogether.gotogethersbe.web.api.DefaultRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final MemberRepository memberRepository;

    //로그인
    public DefaultRes login(LoginDto.LoginRequest request){
        Member member = memberRepository.findByEmail(request.getEmail()).orElseThrow();
    }
}
