package com.gotogether.gotogethersbe.domain;

import com.gotogether.gotogethersbe.domain.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CURATION_ID")
    private Long id;

    private String ages;
    @Enumerated(EnumType.STRING)
    private GenderGroup genderGroup;

    @Enumerated(EnumType.STRING)
    private Companion companion;

    @Enumerated(EnumType.STRING)
    private Religion religion;

    @Enumerated(EnumType.STRING)
    private Theme theme;

    @Builder
    public Curation(String ages, GenderGroup genderGroup, Companion companion, Religion religion, Theme theme){
        this.ages = ages;
        this.genderGroup = genderGroup;
        this.companion = companion;
        this.religion = religion;
        this.theme = theme;
    }
}
