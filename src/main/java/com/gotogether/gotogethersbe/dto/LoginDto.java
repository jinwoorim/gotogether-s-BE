package com.gotogether.gotogethersbe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.sql.Date;

public class LoginDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class LoginRequest {
        private String email;
        private String password;

        public UsernamePasswordAuthenticationToken toAuthentication() {
            return new UsernamePasswordAuthenticationToken(email, password);
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class LoginResponse {
        private Long id;
        private String email;
        private String name;
        private Date birth;
        private String gender;
        private String age;
        private String companion;
        private String religion;
        private String groupType;
        private String theme;
        private String season;
        private String interest;

    }
}
