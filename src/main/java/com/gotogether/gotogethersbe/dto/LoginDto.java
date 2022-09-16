package com.gotogether.gotogethersbe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class LoginDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class LoginRequest {
        private String email;
        private String password;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class LoginResponse {
        private Long member_id;
        private String email;
        private String name;
        private Date birth;
        private int age;
        private String compaion;
        private String religion;
        private String group;
        private String theme;
        private String period;
        private String gender;
        private String interest;
    }
}
