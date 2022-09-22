package com.gotogether.gotogethersbe.controller;

import com.gotogether.gotogethersbe.dto.MemberDto;
import com.gotogether.gotogethersbe.service.MemberService;
import com.gotogether.gotogethersbe.web.api.DefaultRes;
import com.gotogether.gotogethersbe.web.api.ResponseMessage;
import com.gotogether.gotogethersbe.web.api.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    //회원 상세 정보 조회
    @GetMapping("/members/detail")
    public DefaultRes<MemberDto.MemberResponse> getMember(){
        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_USER, memberService.getMyInfo());
    }

    //비밀번호 변경 전 비밀번호 확인
    @PostMapping("/members/confirm")
    public DefaultRes confirmPassword(@RequestBody MemberDto.passwordRequest request){
        if(memberService.confirmPassword(request)){
            return DefaultRes.res(StatusCode.OK, ResponseMessage.CONFIRM_PASSWORD_SUCCESS);
        }
        return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.CONFIRM_PASSWORD_FAIL);
    }

    //비밀번호 변경 후 저장
    @PutMapping("/members/detail")
    public DefaultRes changePassword(@RequestBody MemberDto.passwordRequest request){
        memberService.changePassword(request);
        return DefaultRes.res(StatusCode.OK, ResponseMessage.UPDATE_MEMBER_SUCCESS);
    }
}
