package com.gotogether.gotogethersbe.service;

import com.gotogether.gotogethersbe.config.common.exception.CustomException;
import com.gotogether.gotogethersbe.config.util.SecurityUtil;
import com.gotogether.gotogethersbe.domain.Member;
import com.gotogether.gotogethersbe.dto.MemberDto;
import com.gotogether.gotogethersbe.repository.MemberRepository;
import com.gotogether.gotogethersbe.web.api.ResponseMessage;
import com.gotogether.gotogethersbe.web.api.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 현재 SecurityContext 에 있는 유저 정보 가져오기
    @Transactional(readOnly = true)
    public MemberDto.MemberResponse getMyInfo() {
        return memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .map(MemberDto.MemberResponse::of)
                .orElseThrow(() -> new CustomException(ResponseMessage.NOT_FOUND_USER,StatusCode.FORBIDDEN));
    }

    //패스워드 변경 전 확인
    @Transactional(readOnly = true)
    public boolean confirmPassword(MemberDto.passwordRequest request){
        Member findMember = memberRepository.findById(SecurityUtil.getCurrentMemberId())
                        .orElseThrow(() -> new CustomException("잘못된 접근 방법입니다.", StatusCode.FORBIDDEN));
        return passwordEncoder.matches(request.getPassword(), findMember.getPassword());
    }

    //패스워드 변경
    @Transactional
    public void changePassword(MemberDto.passwordRequest request){
        Member findMember = memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new CustomException("잘못된 접근 방법입니다.", StatusCode.FORBIDDEN));
        memberRepository.save(findMember.updatePassword(passwordEncoder.encode(request.getPassword())));
    }
}