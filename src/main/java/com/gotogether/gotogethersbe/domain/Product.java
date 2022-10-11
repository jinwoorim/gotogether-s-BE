package com.gotogether.gotogethersbe.domain;

import com.gotogether.gotogethersbe.domain.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;
    private String thumbnail;
    private String productName;
    private String basicPrice;
    private String country;
    private String region;
    private String airport;
    private String points;
    private String info;
    private String ages;
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

    @OneToMany(fetch = FetchType.LAZY)
    private List<ProductOption> productOptionList = new ArrayList<>();


    @Builder
    public Product(Long id, String thumbnail, String productName, String basicPrice, String country,
                   String region, String points, String airport, String info, String ages,
                   Companion companion, Continent continent, GenderGroup genderGroup,
                   Religion religion, Theme theme, List<ProductOption> productOptionList){

        this.id = id;
        this.thumbnail = thumbnail;
        this.productName = productName;
        this.basicPrice = basicPrice;
        this.country = country;
        this.region = region;
        //요약설명쪽 정보
        this.points = points;
        this.airport = airport;
        this.info = info;
        // 카테고리 쪽 정보
        this.ages = ages;
        this.companion = companion;
        this.continent = continent;
        this.genderGroup = genderGroup;
        this.religion = religion;
        this.theme = theme;
        this.productOptionList = productOptionList;
    }

}
