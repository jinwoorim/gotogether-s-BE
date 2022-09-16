package com.gotogether.gotogethersbe.controller;

import com.gotogether.gotogethersbe.dto.LoginDto;
import com.gotogether.gotogethersbe.service.AuthService;
import com.gotogether.gotogethersbe.web.api.DefaultRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    //로그인
    @PostMapping("/login")
    @ResponseBody
    public DefaultRes login(@RequestBody LoginDto.LoginRequest request){

        return authService.login(request);
    }
}
