package com.gotogether.gotogethersbe.dto;

import com.gotogether.gotogethersbe.domain.Member;
import com.gotogether.gotogethersbe.domain.enums.Authority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

public class MemberDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemberRequest {
        private String email;
        private String password;
        private String name;
        private LocalDate birth;

        public Member toMember(PasswordEncoder passwordEncoder) {
            return Member.builder()
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .name(name)
                    .authority(Authority.ROLE_USER)
                    .build();
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemberResponse {
        private Long id;
        private String email;
        private String name;
        private LocalDate birth;

        public MemberResponse(Long id){
            this.id=id;
        }

        public static MemberResponse of(Member member) {
            return new MemberResponse(member.getId());
        }
    }
}
