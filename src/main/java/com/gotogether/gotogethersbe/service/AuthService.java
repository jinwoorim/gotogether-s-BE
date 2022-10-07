package com.gotogether.gotogethersbe.service;

import com.gotogether.gotogethersbe.config.auth.TokenManager;
import com.gotogether.gotogethersbe.config.common.exception.CustomException;
import com.gotogether.gotogethersbe.domain.Member;
import com.gotogether.gotogethersbe.dto.LoginDto;
import com.gotogether.gotogethersbe.dto.MemberDto;
import com.gotogether.gotogethersbe.dto.TokenDto;
import com.gotogether.gotogethersbe.repository.MemberRepository;
import com.gotogether.gotogethersbe.web.api.ResponseMessage;
import com.gotogether.gotogethersbe.web.api.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.concurrent.TimeUnit;


@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenManager tokenManager;
    private final RedisTemplate redisTemplate;

    //회원 가입
    @Transactional
    public MemberDto.MemberResponse signup(MemberDto.MemberRequest request){
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new CustomException(ResponseMessage.CHECK_EMAIL_FAIL, StatusCode.BAD_REQUEST);
        }
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

        //RefreshToken Redis에 저장
        redisTemplate.opsForValue()
                .set("RT:" + authentication.getName(), tokenDto.getRefreshToken(), tokenDto.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);

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

        // Redis에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        String refreshToken = (String) redisTemplate.opsForValue().get("RT:"+authentication.getName());

        // 로그아웃한 경우 확인
       if(ObjectUtils.isEmpty(refreshToken)){
            throw new CustomException("로그아웃된 유저입니다.", StatusCode.UNAUTHORIZED);
        }

        // Refresh Token 일치하는지 검사
        if (!refreshToken.equals(tokenRequestDto.getRefreshToken())) {
            throw new CustomException("토큰의 유저 정보가 일치하지 않습니다.", StatusCode.UNAUTHORIZED);
        }

        // 새로운 토큰 생성
        TokenDto tokenDto = tokenManager.generateTokenDto(authentication);

        // Redis 정보 업데이트
        redisTemplate.opsForValue()
                .set("RT:" + authentication.getName(), tokenDto.getRefreshToken(), tokenDto.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);

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

        //Redis에서 해당 이름으로 된 Refresh 토큰이 있다면 삭제
        if(redisTemplate.opsForValue().get("RT:"+authentication.getName()) != null){
            redisTemplate.delete("RT:" + authentication.getName());
        }

        //Access Token 유효시간과 함께 Redis 블랙리스트에 저장
        Long expiration = tokenManager.getExpiration(tokenRequestDto.getAccessToken());
        redisTemplate.opsForValue()
                .set(tokenRequestDto.getAccessToken(), "logout", expiration, TimeUnit.MILLISECONDS);
    }
}