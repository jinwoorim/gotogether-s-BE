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
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;

    private String thumbnail;
    private String productName;
    private long amount;
    private String country;
    private String region;
    private String airport;
    private String points;
    private String info;

    @OneToMany(mappedBy = "product", cascade=CascadeType.REMOVE)
    private List<Option> optionList;

    @Enumerated(EnumType.STRING)
    private Ages ages;
    @Enumerated(EnumType.STRING)
    private Companion companion;
    @Enumerated(EnumType.STRING)
    private Continent continent;
    @Enumerated(EnumType.STRING)
    private GenderGroup genderGroup;
    @Enumerated(EnumType.STRING)
    private Religion religion;
    @Enumerated(EnumType.STRING)
    private Theme theme;

    @Builder
    public Product(String thumbnail, String productName, Long amount, String country,
                   String region, String points, String airport, String info, Ages ages,
                   Companion companion, Continent continent, GenderGroup genderGroup,
                   Religion religion, Theme theme, List<Option> optionList){

        this.thumbnail = thumbnail;
        this.productName = productName;
        this.amount = amount;
        this.country = country;
        this.info = info;
        this.optionList = optionList;
        //요약설명쪽 정보
        this.points = points;
        this.airport = airport;
        this.region = region;
        // 카테고리 쪽 정보
        this.ages = ages;
        this.companion = companion;
        this.continent = continent;
        this.genderGroup = genderGroup;
        this.religion = religion;
        this.theme = theme;
    }
}
