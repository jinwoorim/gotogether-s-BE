package com.gotogether.gotogethersbe.service;

import com.gotogether.gotogethersbe.config.auth.TokenManager;
import com.gotogether.gotogethersbe.config.common.exception.CustomException;
import com.gotogether.gotogethersbe.domain.Member;
import com.gotogether.gotogethersbe.domain.RefreshToken;
import com.gotogether.gotogethersbe.dto.LoginDto;
import com.gotogether.gotogethersbe.dto.MemberDto;
import com.gotogether.gotogethersbe.dto.TokenDto;
import com.gotogether.gotogethersbe.repository.MemberRepository;
import com.gotogether.gotogethersbe.repository.RefreshTokenRepository;
import com.gotogether.gotogethersbe.web.api.ResponseMessage;
import com.gotogether.gotogethersbe.web.api.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenManager tokenManager;
    private final RefreshTokenRepository refreshTokenRepository;

    //회원 가입
    @Transactional
    public MemberDto.MemberResponse signup(MemberDto.MemberRequest request){
        //패스워드 암호화 후 저장
        Member member = request.toMember(passwordEncoder);
        return new MemberDto.MemberResponse(memberRepository.save(member));
    }

    //이메일 유효성 검사
    @Transactional
    public void checkEmail(MemberDto.emailRequest request){
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new CustomException(ResponseMessage.CHECK_EMAIL_FAIL, StatusCode.BAD_REQUEST);
        }
    }

    // 로그인
    @Transactional
    public TokenDto login(LoginDto.LoginRequest loginRequest) {
        // Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = loginRequest.toAuthentication();

        // 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        // authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        //인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenManager.generateTokenDto(authentication);

        //RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        // 토큰 발급
        return tokenDto;
    }

    //토큰 재발행
    @Transactional
    public TokenDto reissue(TokenDto tokenRequestDto) {
        // Refresh Token 검증
        if (!tokenManager.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new CustomException("Refresh Token 이 유효하지 않습니다.", StatusCode.UNAUTHORIZED);
        }

        // Access Token 에서 Member ID 가져오기
        Authentication authentication = tokenManager.getAuthentication(tokenRequestDto.getAccessToken());

        // 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new CustomException("로그아웃 된 사용자입니다.", StatusCode.UNAUTHORIZED));

        // Refresh Token 일치하는지 검사
        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new CustomException("토큰의 유저 정보가 일치하지 않습니다.", StatusCode.UNAUTHORIZED);
        }

        // 새로운 토큰 생성
        TokenDto tokenDto = tokenManager.generateTokenDto(authentication);

        // 저장소 정보 업데이트
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // 토큰 발급
        return tokenDto;
    }

    //로그아웃
    @Transactional
    public void logout(TokenDto tokenRequestDto){
        //Access Token 유효성 확인
        if(!tokenManager.validateToken(tokenRequestDto.getAccessToken())){
            throw new CustomException("Access Token이 유효하지 않습니다.", StatusCode.UNAUTHORIZED);
        }
        //Refresh Token 유효성 확인
        Authentication authentication = tokenManager.getAuthentication(tokenRequestDto.getAccessToken());

        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new CustomException("이미 로그아웃한 사용자입니다.", StatusCode.UNAUTHORIZED));

        //Refresh Toke 삭제
        refreshTokenRepository.delete(refreshToken);
    }
}