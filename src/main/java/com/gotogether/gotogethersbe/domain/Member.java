package com.gotogether.gotogethersbe.domain;

import com.gotogether.gotogethersbe.domain.enums.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth; //생일

    @Enumerated(EnumType.STRING)
    private Authority authority; //권한

    @Builder
    public Member(String email, String password, String name, LocalDate birth, Authority authority) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.authority = authority;
    }
}
