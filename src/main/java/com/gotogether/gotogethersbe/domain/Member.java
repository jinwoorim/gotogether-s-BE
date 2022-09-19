package com.gotogether.gotogethersbe.domain;

import com.gotogether.gotogethersbe.domain.enums.Authority;
import com.gotogether.gotogethersbe.domain.enums.Gender;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //기본키

    @Column(nullable = false)
    private String email; //이메일

    @Column(nullable = false)
    private String password; //비밀번호

    @Column(nullable = false)
    private String name; //이름

    private Date birth; //생일

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Enumerated(EnumType.STRING)
    private Gender gender; //성별(MALE, FEMALE)

    private String age; //연령대 그룹

    private String companion; //동행자 유형

    private String religion; //종교적 성향

    private String groupType; //그룹 유형

    private String theme; //여행 테마

    private String season; //여행 시기

    private String interest; //관심있는 여행지

    @Builder
    public Member(String email, String password, Authority authority) {
        this.email = email;
        this.password = password;
        this.authority = authority;
    }
}
