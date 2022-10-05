package com.gotogether.gotogethersbe.controller;

import com.gotogether.gotogethersbe.dto.LoginDto;
import com.gotogether.gotogethersbe.dto.MemberDto;
import com.gotogether.gotogethersbe.dto.TokenDto;
import com.gotogether.gotogethersbe.service.AuthService;
import com.gotogether.gotogethersbe.web.api.DefaultRes;
import com.gotogether.gotogethersbe.web.api.ResponseMessage;
import com.gotogether.gotogethersbe.web.api.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    //회원가입
    @PostMapping("/members")
    public DefaultRes signup(@RequestBody MemberDto.MemberRequest request){
        authService.signup(request);
        return DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_USER);
    }

    //이메일 유효성 검사
    @PostMapping("/members/validation")
    public DefaultRes checkEmail(@RequestBody MemberDto.emailRequest request){
        authService.checkEmail(request);
        return DefaultRes.res(StatusCode.OK, ResponseMessage.CHECK_EMAIL_SUCCESS);
    }

    //로그인
    @PostMapping("/login")
    public DefaultRes<TokenDto> login(@RequestBody LoginDto.LoginRequest request){
        return DefaultRes.res(StatusCode.OK, ResponseMessage.LOGIN_SUCCESS, authService.login(request));
    }

    //토큰 재발급
    @PostMapping("/reissue")
    public DefaultRes<TokenDto> reissue(@RequestBody TokenDto tokenRequestDto) {
        return DefaultRes.res(StatusCode.OK, ResponseMessage.REISSUE_SUCCESS,authService.reissue(tokenRequestDto));
    }

    //로그아웃
    @PostMapping("/logout")
    public DefaultRes logout(@RequestBody TokenDto tokenRequestDto){
        authService.logout(tokenRequestDto);
        return DefaultRes.res(StatusCode.OK, ResponseMessage.LOGOUT_SUCCESS);
    }
}
