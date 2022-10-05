package com.gotogether.gotogethersbe.domain;

import com.gotogether.gotogethersbe.domain.enums.Authority;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Data
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id; //기본키

    @OneToOne
    @JoinColumn(name = "CURATION_ID")
    private Curation curation; //큐레이션키

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


    //회원 비밀번호 변경
    public Member updatePassword(String password) {
        this.password=password;
        return this;
    }

    //큐레이션 정보 연결
    public void addCuration(Curation curation){
        this.curation=curation;
    }
}
