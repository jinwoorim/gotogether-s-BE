package com.gotogether.gotogethersbe.service;

import com.gotogether.gotogethersbe.domain.Member;
import com.gotogether.gotogethersbe.dto.LoginDto;
import com.gotogether.gotogethersbe.repository.MemberRepository;
import com.gotogether.gotogethersbe.web.api.DefaultRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
